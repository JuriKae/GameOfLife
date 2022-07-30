package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GoLMain extends JLayeredPane {

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

    private static Cell lastCell;

    private static GoLMain main;

    private static Thread thread;

    private static boolean reset;
    private static boolean repainted;
    private static boolean initialized;

    private static double zoomFactor = 1;
    private static double prevZoomFactor = 1;
    private static boolean hasZoomed;

    private static double xOffset = 0;
    private static double yOffset = 0;

    private static Graphics2D g2 = null;

    private static MouseCellListener mouseListener = new MouseCellListener();

    public GoLMain() {
        frame = new JFrame();
        frame.setTitle("Game of Life");
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 255), 2));
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.addMouseWheelListener(mouseListener);

        topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.setPreferredSize(new Dimension(panelWidth, topPanelHeight));

        frame.add(this, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);

        new PatternPanel();
        new BasicOptions();
        new AdvancedOptions();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        main = new GoLMain();
        resetSuff();
    }

    public static void createThread() {
        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (!reset) {

                    try {
                        // do not sleep if user makes steps
                        if (!BasicOptions.isStep()) {
                            Thread.sleep(BasicOptions.getDelay());
                        }

                        // wait until all the cells have been repainted
                        while (!repainted) {
                            Thread.sleep(0);
                        }
                        repainted = false;

                        // wait if the game has been paused
                        while (BasicOptions.isPaused() && !reset) {
                            Thread.sleep(0);
                            // if user pressed step, break out of the while loop for one iteration
                            if (BasicOptions.isStep()) {
                                BasicOptions.setStep(false);
                                break;
                            }
                        }

                        // generate colors for one-generation color mode
                        CellColor.generateColors();

                        Cell.countNeighbours();

                        main.repaint();
                        generation++;
                        BasicOptions.getGenerationLabel().setText("Generation: " + generation);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // here when user resets
                resetSuff();
            }
        });
        // thread always starts immediately, but paused is enabled
        thread.start();
    }

    public static void resetSuff() {
        reset = false;
        initialized = false;
        repainted = false;
        generation = 1;
        BasicOptions.getGenerationLabel().setText("Generation: " + generation);

        CellColor.generateColors();
        Cell.initializeCells();

        currentCellWidth = Cell.getCellWidth();
        currentCellHeight = Cell.getCellHeight();

        createThread();

        main.repaint();
    }

    public static void resetZoom() {
        hasZoomed = true;
        zoomFactor = 1;
        prevZoomFactor = 1;
        xOffset = 0;
        yOffset = 0;

        main.repaint();
    }

    // is called when user is dragging the mouse so that more pixel will be
    // recognized
    // only the pixels that have been painted will be repainted here
    // not calling super.paint(), because that would reset everything
    public void paintWithMouse(Graphics g, int x, int y, Cell cell, Boolean isLeftClick) {

        g2 = (Graphics2D) g;

        AffineTransform at = new AffineTransform();
        at.translate(xOffset, yOffset);
        at.scale(zoomFactor, zoomFactor);
        g2.transform(at);

        g2.setColor(cell.isNextGenAlive() == AdvancedOptions.isColorsInverted() ? cell.getDeadColor() : cell.getAliveColor());
        g2.fill(cell);

        if (lastCell != null) {
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
                    g2.fill(list.get(i));
                    list.get(i).setAlive(isLeftClick);
                    list.get(i).setNextGenAlive(isLeftClick);
                }
            }
        }
        lastCell = cell;
    }

    @Override
    public void paintComponent(Graphics g) {
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
        // been initalized with the correct values
        while (!initialized) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Cell[][] cells = Cell.getCells();
        int xGrids = cells.length;
        int yGrids = cells[0].length;

        Cell cell = null;

        for (int i = 0; i < xGrids; i++) {
            for (int j = 0; j < yGrids; j++) {

                cell = cells[i][j];

                // set color depending on status of cell and check if colors are inverted
                g2.setColor(cell.isNextGenAlive() == AdvancedOptions.isColorsInverted() ? cell.getDeadColor() : cell.getAliveColor());
                g2.fill(cell);

                if (!hasZoomed) {
                    // save if last generation was alive or dead
                    cell.setLastGenAlive(cell.isAlive());

                    // set cell alive if it is alive in the next generation
                    cell.setAlive(cell.isNextGenAlive());
                }
            }
        }

        // show the grid if user enabled it
        if (AdvancedOptions.isShowGrid()) {
            main.setLayer(this, JLayeredPane.PALETTE_LAYER);
            g2.setColor(Color.DARK_GRAY);
            g2.setColor(new Color(50, 50, 50, 75));

            for (int i = 0; i < yGrids; i++) {
                g2.drawLine(0, i * currentCellHeight, xGrids * currentCellWidth, i * currentCellHeight);
            }

            for (int i = 0; i < xGrids; i++) {
                g2.drawLine(i * currentCellWidth, 0, i * currentCellWidth, yGrids * currentCellHeight);
            }
            main.setLayer(this, JLayeredPane.DEFAULT_LAYER);
        }

        hasZoomed = false;
        repainted = true;
    }

    public static JPanel getTopPanel() {
        return topPanel;
    }

    public static Thread getThread() {
        return thread;
    }

    public static GoLMain getMain() {
        return main;
    }

    public static void setReset(boolean reset) {
        GoLMain.reset = reset;
    }

    public static void setGeneration(int generation) {
        GoLMain.generation = generation;
    }

    public static int getGeneration() {
        return generation;
    }

    public static void setHasZoomed(boolean hasZoomed) {
        GoLMain.hasZoomed = hasZoomed;
    }

    public static double getZoomFactor() {
        return zoomFactor;
    }

    public static void setZoomFactor(double zoomFactor) {
        GoLMain.zoomFactor = zoomFactor;
    }

    public static double getxOffset() {
        return xOffset;
    }

    public static double getyOffset() {
        return yOffset;
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void setInitialized(boolean initialized) {
        GoLMain.initialized = initialized;
    }

    public static void setLastCell(Cell lastCell) {
        GoLMain.lastCell = lastCell;
    }
}