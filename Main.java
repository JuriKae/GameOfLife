import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

    private static JFrame frame;
    private static JPanel topPanel;

    private static int panelWidth = 500;
    private static int panelHeight = 500;

    private static int width = panelWidth + 16;
    private static int height = panelHeight + 39 + 75;

    private static int xGrids;
    private static int yGrids;

    private static int generation = 1;

    private static Main main;

    private static Thread thread;

    private static boolean reset;
    private static boolean repainted;
    private static boolean initialized;

    public Main() {
        frame = new JFrame();
        frame.setTitle("Game of Life");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 255), 1));

        topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.setPreferredSize(new Dimension(0, 75));

        frame.add(this, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);

        new BasicOptions();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.repaint();
    }

    public static void main(String[] args) {
        main = new Main();
        Cell.initializeCells();

        createThread();
    }

    public static void createThread() {
        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (!reset) {
                    try {
                        Thread.sleep(50);
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

                    while (BasicOptions.isPaused() && !reset) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (BasicOptions.isStep()) {
                            BasicOptions.setStep(false);
                            break;
                        }
                    }

                    // long start = System.nanoTime();

                    /* //  this is faster for smaller grids
                    for (int i = 1; i < xGrids - 1; i++) {
                        for (int j = 1; j < yGrids - 1; j++) {
                            Cell.countNeighbours(Cell.getCells()[i][j], i, j);
                        }
                    } */

                    Cell.makeCountingThreads();

                    for (Thread t : Thread.getAllStackTraces().keySet()) {
                        if (t.getName().equals("1") || t.getName().equals("2") || t.getName().equals("3") || t.getName().equals("4")) {
                            try {
                                t.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    // long duration = (System.nanoTime() - start) / 1000000;
                    // System.out.println(duration + "ms for counting");

                    main.repaint();
                    generation++;
                    BasicOptions.getGenerationLabel().setText("Generation: " + generation);
                }
                // resetted
                reset = false;
                generation = 1;
                BasicOptions.getGenerationLabel().setText("Generation: " + generation);
                System.out.println("Ended this Thread " + Thread.currentThread());
            }
        });
        thread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw first alive cells
        if (initialized) {
            // draw cells
            for (int i = 1; i < xGrids - 1; i++) {
                for (int j = 1; j < yGrids - 1; j++) {
                    int x = (int) (Cell.getCells()[i][j].getX());
                    int y = (int) (Cell.getCells()[i][j].getY());
                    if (Cell.getCells()[i][j].isNextGenAlive()) {
                        g.setColor(Cell.getCells()[i][j].getAliveColor());
                        Cell.getCells()[i][j].setAlive(true);
                    } else {
                        g.setColor(Cell.getCells()[i][j].getDeadColor());
                        Cell.getCells()[i][j].setAlive(false);
                    }
                    g.fillRect(x, y, Cell.getCellWidth(), Cell.getCellHeight());
                }
            }
        } else {
            Cell.initAliveCells();
            
            for (int i = 0; i < xGrids - 1; i++) {
                for (int j = 0; j < yGrids - 1; j++) {
                    if (Cell.getCells()[i][j].isNextGenAlive()) {
                        int x = (int) (Cell.getCells()[i][j].getX());
                        int y = (int) (Cell.getCells()[i][j].getY());
                        g.setColor(Cell.getCells()[i][j].getAliveColor());
                        g.fillRect(x, y, Cell.getCellWidth(), Cell.getCellHeight());
                        Cell.getCells()[i][j].setNextGenAlive(true);
                    }
                }
            }
            initialized = true;
        }

        repainted = true;
    }

    public static int getPanelWidth() {
        return panelWidth;
    }

    public static int getPanelHeight() {
        return panelHeight;
    }

    public static int getxGrids() {
        return xGrids;
    }

    public static int getyGrids() {
        return yGrids;
    }

    public static JPanel getTopPanel() {
        return topPanel;
    }

    public static Thread getThread() {
        return thread;
    }

    public static void setThread(Thread thread) {
        Main.thread = thread;
    }

    public static Main getMain() {
        return main;
    }

    public static void setInitialized(boolean initialized) {
        Main.initialized = initialized;
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void setxGrids(int xGrids) {
        Main.xGrids = xGrids;
    }

    public static void setyGrids(int yGrids) {
        Main.yGrids = yGrids;
    }

    public static boolean isReset() {
        return reset;
    }

    public static void setReset(boolean reset) {
        Main.reset = reset;
    }
}