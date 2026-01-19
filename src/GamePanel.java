package src;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private static JFrame frame;
    private static JPanel topPanel;

    private static int panelWidth = 600;
    private static int panelHeight = 600;

    private static final int topPanelHeight = 75;

    private static int frameWidth = panelWidth + 16 + PatternPanel.getPatternpanelwidth();
    private static int frameHeight = panelHeight + 39 + topPanelHeight;

    private static int currentCellWidth;
    private static int currentCellHeight;

    private static int generation = 1;

    private Cell lastCell;

    private Thread thread;

    private static boolean reset;
    private boolean inPaint;
    private boolean repainted;
    private boolean initialized;

    private double zoomFactor = 1;
    private double prevZoomFactor = 1;
    private boolean hasZoomed;

    private double xOffset = 0;
    private double yOffset = 0;

    private static Graphics2D g2 = null;

    private BufferedImage gridImage;
    private int[] pixelData;

    public GamePanel() {
        frame = new JFrame();
        frame.setTitle("Game of Life");
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 255), 2));

        topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.setPreferredSize(new Dimension(panelWidth, topPanelHeight));

        frame.add(this, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);

        new PatternPanel(this);
        new BasicOptions(this);
        new AdvancedOptions(this);
        MouseCellListener mouseListener = new MouseCellListener(this);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.addMouseWheelListener(mouseListener);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        resetSuff();
    }

    public void createThread() {
        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                runThread();
            }
        });
        // thread always starts immediately, but "paused" is enabled
        thread.start();
    }

    public void runThread() {
        while (!reset) {

            try {
                // do not sleep if user makes steps
                if (!BasicOptions.isStep()) {
                    Thread.sleep(BasicOptions.getDelay());
                } else {
                    BasicOptions.setStep(false);
                }

                // wait until all the cells have been repainted
                while (!repainted) {
                    Thread.sleep(0);
                }
                repainted = false;

                // wait if the game has been paused
                while (BasicOptions.isPaused() && !reset) {
                    Thread.sleep(1);
                    // if user pressed step, break out of the while loop for one iteration
                    if (BasicOptions.isStep()) {
                        break;
                    }
                }

                // generate colors for one-generation color mode
                CellColor.generateColors(System.nanoTime());

                while (inPaint) {
                    Thread.sleep(0);
                }

                Cell.countNeighbors();
                CellColor.callChangeColorFunction();

                Cell cell = null;
                Cell[][] cells = Cell.getCells();
                int xGrids = cells.length;
                int yGrids = cells[0].length;

                for (int i = 0; i < xGrids; i++) {
                    for (int j = 0; j < yGrids; j++) {
                        cell = cells[i][j];

                        // save if last generation was alive or dead
                        cell.setLastGenAlive(cell.isAlive());

                        // set cell alive if it is alive in the next generation
                        cell.setAlive(cell.isNextGenAlive());
                    }
                }
                this.repaint();
                generation++;
                BasicOptions.getGenerationLabel().setText("Generation: " + generation);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // here when user resets
        resetSuff();
    }

    public void resetSuff() {
        reset = false;
        initialized = false;
        repainted = false;
        generation = 1;
        BasicOptions.getGenerationLabel().setText("Generation: " + generation);

        CellColor.generateColors(System.nanoTime());
        Cell.initializeCells(this.getWidth(), this.getHeight());
        
        initializeBuffer(Cell.getxGrids(), Cell.getyGrids());
        
        initialized = true;

        currentCellWidth = Cell.getCellWidth();
        currentCellHeight = Cell.getCellHeight();

        createThread();
    }

    public void resetZoom() {
        hasZoomed = true;
        zoomFactor = 1;
        prevZoomFactor = 1;
        xOffset = 0;
        yOffset = 0;
    }

    public int oneGenerationBack() {
        generation--;
        this.repaint();
        return generation;
    }

    public void initializeBuffer(int width, int height) {
        // Create an image compatible with the screen
        gridImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Get the underlying array. Writing to this array updates the image instantly.
        pixelData = ((DataBufferInt) gridImage.getRaster().getDataBuffer()).getData();
    }

    // is called when user is dragging the mouse so that more pixel will be drawn
    // only the pixels that have been touched will be repainted here
    // not calling super.paint(), because that would reset everything
    public void paintWithMouse(Graphics g, int x, int y, Cell cell, Boolean isLeftClick, Boolean wasDragged) {

        g2 = (Graphics2D) g;

        AffineTransform at = new AffineTransform();
        at.translate(xOffset, yOffset);
        at.scale(zoomFactor, zoomFactor);
        g2.transform(at);

        boolean isColorsInverted = AdvancedOptions.isColorsInverted();
        g2.setColor(cell.isAlive() == isColorsInverted ? cell.getDeadColor() : cell.getAliveColor());
        g2.fillRect(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());

        if (lastCell != null && wasDragged) {
            // if the drawn cell is not next to the cell drawn before
            // calculating the line between these cell with the Bresenham algorithm
            if (new Point((int) lastCell.getCenterX(), (int) lastCell.getCenterY()).distance(cell.getCenterX(),
                    cell.getCenterY()) > currentCellWidth) {

                int lastCellX = (int) lastCell.getX() / currentCellWidth;
                int lastCellY = (int) lastCell.getY() / currentCellHeight;

                int cellxGrid = (int) cell.getX() / currentCellWidth;
                int cellyGrid = (int) cell.getY() / currentCellHeight;

                ArrayList<Cell> list = Bresenham.findLine(Cell.getCells(), lastCellX, lastCellY, cellxGrid, cellyGrid);

                for (int i = 0; i < list.size(); i++) {
                    Cell lineCell = list.get(i);
                    g2.fillRect(lineCell.getX(), lineCell.getY(), lineCell.getWidth(), lineCell.getHeight());
                    lineCell.setAlive(isLeftClick);
                }
            }
        }
        lastCell = cell;

        int xGrids = Cell.getxGrids();
        int yGrids = Cell.getyGrids();

        // show the grid if user enabled it
        if (AdvancedOptions.isShowGrid()) {
            g2.setColor(new Color(14, 14, 14));
            g2.setStroke(new BasicStroke(0));
            for (int i = 0; i < yGrids; i++) {
                g2.drawLine(0, i * currentCellHeight, xGrids * currentCellWidth, i * currentCellHeight);
            }

            for (int i = 0; i < xGrids; i++) {
                g2.drawLine(i * currentCellWidth, 0, i * currentCellWidth, yGrids * currentCellHeight);
            }
            g2.setStroke(new BasicStroke(1));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        hasZoomed = MouseCellListener.isHasZoomed();
        inPaint = true;
        super.paintComponent(g);

        g2 = (Graphics2D) g;

        AffineTransform at = new AffineTransform();
        if (hasZoomed) {

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = zoomFactor / prevZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            prevZoomFactor = zoomFactor;
        }

        at.translate(xOffset, yOffset);
        at.scale(zoomFactor, zoomFactor);
        g2.transform(at);

        // initialized is set true by the Cell.java class when the 2D cell array has
        // been initialized with the correct values
        while (!initialized) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        boolean isColorsInverted = AdvancedOptions.isColorsInverted();
        Cell[][] cells = Cell.getCells();
        int xGrids = cells.length;
        int yGrids = cells[0].length;

        for (int i = 0; i < xGrids; i++) {
            for (int j = 0; j < yGrids; j++) {
                Cell cell = cells[i][j];

                // Get color depending on status of cell and check if colors are inverted
                Color c = cell.isAlive() == isColorsInverted ? cell.getDeadColor() : cell.getAliveColor();
                
                // Map the 2D grid state to the 1D image pixel array
                pixelData[j * xGrids + i] = c.getRGB();
            }
        }

        // Render the entire grid as a single image scaled to the cell dimensions
        g2.drawImage(gridImage, 0, 0, xGrids * currentCellWidth, yGrids * currentCellHeight, null);

        if (AdvancedOptions.isShowGrid()) {
            // make grid as small as possible
            g2.setStroke(new BasicStroke(0));
            g2.setColor(new Color(14, 14, 14));

            for (int i = 0; i < yGrids; i++) {
                g2.drawLine(0, i * currentCellHeight, xGrids * currentCellWidth, i * currentCellHeight);
            }

            for (int i = 0; i < xGrids; i++) {
                g2.drawLine(i * currentCellWidth, 0, i * currentCellWidth, yGrids * currentCellHeight);
            }
            // reset the stroke to 1
            g2.setStroke(new BasicStroke(1));
        }
        MouseCellListener.setHasZoomed(false);
        repainted = true;
        inPaint = false;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public static void setReset(boolean reset) {
        GamePanel.reset = reset;
    }

    public void setHasZoomed(boolean hasZoomed) {
        this.hasZoomed = hasZoomed;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }

    public double getxOffset() {
        return xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setLastCell(Cell lastCell) {
        this.lastCell = lastCell;
    }
}