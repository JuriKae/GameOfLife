package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

    private static JFrame frame;
    private static JPanel topPanel;

    private static int panelWidth = 600;
    private static int panelHeight = 600;

    private static final int topPanelHeight = 75;

    private static int width = panelWidth + 16;
    private static int height = panelHeight + 39 + topPanelHeight;

    private static int xGrids;
    private static int yGrids;

    private Cell cell;

    private static int currentCellWidth;
    private static int currentCellHeight;

    private static int generation = 1;

    private static Main main;

    private static Thread thread;

    private static boolean isThread;

    private static boolean reset;
    private static boolean repainted;

    private static double zoomFactor = 1;
    private static double prevZoomFactor = 1;
    private static boolean hasZoomed;

    private static double xOffset = 0;
    private static double yOffset = 0;

    private static Graphics2D g2 = null;

    private static MouseCellListener mouseListener = new MouseCellListener();

    public Main() {
        frame = new JFrame();
        frame.setTitle("Game of Life");
        frame.setSize(width, height);
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

        new BasicOptions();
        new AdvancedOptions();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        main = new Main();
        resetSuff();
    }

    public static void createThread(boolean isThread) {

        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (!reset) {
                    try {
                        // do not sleep if user makes steps
                        if (!BasicOptions.isStep()) {
                            Thread.sleep(BasicOptions.getDelay());
                        } else {
                            BasicOptions.setStep(false);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // wait until all the cells have been repainted
                    while (!repainted) {
                        try {
                            Thread.sleep(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    repainted = false;

                    // wait if the game has been paused
                    while (BasicOptions.isPaused() && !reset) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // if user pressed step, break out of the while loop for one iteration
                        if (BasicOptions.isStep()) {
                            break;
                        }
                    }
                    // generate colors for one-generation color mode
                    CellColor.generateColors();

                    if (isThread) {
                        // count all neighbours and assign colors
                        Cell.makeCountingThreads();

                        // wait until all counting threads are finished
                        for (Thread t : Thread.getAllStackTraces().keySet()) {
                            if (t.getName().equals("1") || t.getName().equals("2") || t.getName().equals("3")
                                    || t.getName().equals("4")) {
                                try {
                                    t.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else {

                        for (int i = 1; i < xGrids - 1; i++) {
                            for (int j = 1; j < yGrids - 1; j++) {
                                Cell.countNeighbours(Cell.getCells()[i][j], i, j);
                            }
                        }

                    }

                    main.repaint();
                    generation++;
                    BasicOptions.getGenerationLabel().setText("Generation: " + generation);
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
        generation = 1;
        BasicOptions.getGenerationLabel().setText("Generation: " + generation);

        CellColor.generateColors();
        Cell.initializeCells();

        currentCellWidth = Cell.getCellWidth();
        currentCellHeight = Cell.getCellHeight();

        createThread(isThread);

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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // long start = System.nanoTime();

        g2 = (Graphics2D) g;

        AffineTransform at = new AffineTransform();
        if (hasZoomed) {

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = zoomFactor / prevZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            prevZoomFactor = zoomFactor;
            hasZoomed = false;
        }

        at.translate(xOffset, yOffset);
        at.scale(zoomFactor, zoomFactor);
        g2.transform(at);

        boolean isColorsInverted = AdvancedOptions.isColorsInverted();

        for (int i = 1; i < xGrids - 1; i++) {
            for (int j = 1; j < yGrids - 1; j++) {

                cell = Cell.getCells()[i][j];

                // set color depending on status of cell and check if colors are inverted
                if (cell.isNextGenAlive() == isColorsInverted) {
                    g2.setColor(cell.getDeadColor());
                } else {
                    g2.setColor(cell.getAliveColor());
                }

                int x = (int) cell.getX();
                int y = (int) cell.getY();
                g2.fillRect(x, y, currentCellWidth, currentCellHeight);

                 // save if last generation was alive or dead
                 cell.setLastGenAlive(cell.isAlive());

                 // set cell alive if it is alive in the next generation
                 cell.setAlive(cell.isNextGenAlive());
            }
        }
        repainted = true;
        // long duration = System.nanoTime() - start;
        // System.out.println(duration / 1000000 + "ms");
    }

    public static JPanel getTopPanel() {
        return topPanel;
    }

    public static Thread getThread() {
        return thread;
    }

    public static Main getMain() {
        return main;
    }

    public static void setxGrids(int xGrids) {
        Main.xGrids = xGrids;
    }

    public static void setyGrids(int yGrids) {
        Main.yGrids = yGrids;
    }

    public static void setReset(boolean reset) {
        Main.reset = reset;
    }

    public static void setGeneration(int generation) {
        Main.generation = generation;
    }

    public static int getGeneration() {
        return generation;
    }

    public static void setHasZoomed(boolean hasZoomed) {
        Main.hasZoomed = hasZoomed;
    }

    public static double getZoomFactor() {
        return zoomFactor;
    }

    public static void setZoomFactor(double zoomFactor) {
        Main.zoomFactor = zoomFactor;
    }

    public static double getxOffset() {
        return xOffset;
    }

    public static double getyOffset() {
        return yOffset;
    }

    public static void setThread(boolean isThread) {
        Main.isThread = isThread;
    }
}