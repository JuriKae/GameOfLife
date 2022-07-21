import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

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

    private static Main main;

    private static Thread thread;

    private static boolean initialized;

    public Main() {
        frame = new JFrame();
        frame.setTitle("Game of Life");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.BLACK);

        topPanel = new JPanel();
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setPreferredSize(new Dimension(0, 75));

        frame.add(this, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);

        new Options();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.repaint();
    }

    public static void main(String[] args) {
        main = new Main();
        Cell.initializeCells();
        xGrids = panelWidth / Cell.getCellWidth();
        yGrids = panelHeight / Cell.getCellHeight();

        createThread();
    }

    public static void createThread() {
        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (Options.isPaused()) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (Options.isStep()) {
                            Options.setStep(false);
                            break;
                        }
                    }

                    for (int i = 1; i < xGrids - 1; i++) {
                        for (int j = 1; j < yGrids - 1; j++) {
                            Cell.countNeighbours(Cell.getCells()[i][j], i, j);
                        }
                    }
                    main.repaint();
                }
            }

        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // draw first alive cells
        if (!initialized) {
            g.setColor(Color.WHITE);
            for (int i = 1; i < xGrids - 1; i++) {
                for (int j = 1; j < yGrids - 1; j++) {
                    if (j % 7 == 0 || i % 7 == 0) {
                        int x = (int) (Cell.getCells()[i][j].getX());
                        int y = (int) (Cell.getCells()[i][j].getY());
                        g.fillRect(x, y, 10, 10);
                        Cell.getCells()[i][j].setNextGenAlive(true);
                    }
                }
            }
            initialized = true;
        }

        // draw cells
        for (int i = 1; i < xGrids - 1; i++) {
            for (int j = 1; j < yGrids - 1; j++) {
                int x = (int) (Cell.getCells()[i][j].getX());
                int y = (int) (Cell.getCells()[i][j].getY());
                if (Cell.getCells()[i][j].isNextGenAlive()) {
                    g.setColor(Color.WHITE);
                    Cell.getCells()[i][j].setAlive(true);
                } else {
                    g.setColor(Color.BLACK);
                    Cell.getCells()[i][j].setAlive(false);
                }
                g.fillRect(x, y, Cell.getCellWidth(), Cell.getCellHeight());
            }
        }

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
}