import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

    private static JFrame frame;

    private static int width = 516;
    private static int height = 539;

    private static boolean initialized;

    private static Cell[][] cells = new Cell[500 / 10][500 / 10];

    public Main() {
        frame = new JFrame();
        frame.setTitle("Game of Life");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(500, 500);
        this.setBackground(Color.BLACK);

        frame.add(this);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.repaint();
    }

    public static void main(String[] args) {
        Main main = new Main();
        Cell.initializeCells();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int i = 1; i < 49; i++) {
                        for (int j = 1; j < 49; j++) {
                            Cell currentCell = cells[i][j];
                            countNeighbours(currentCell, i, j);
                        }
                    }
                    main.repaint();
                }
            }

        });

        t1.start();
    }

    public static void countNeighbours(Cell cell, int xGrid, int yGrid) {
        int neighbours = 0;

        // counts alive neighbours of every single cell
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (cells[xGrid + i][yGrid + j].isAlive())
                    if (i != 0 || j != 0) {
                        neighbours++;
                    }
            }
        }

        // sets next gen cell alive or dead according to number of neighbours
        if (cell.isAlive()) {
            if (neighbours <= 1 || neighbours >= 4) {
                cell.setNextGenAlive(false);
            }
        } else {
            if (neighbours == 3) {
                cell.setNextGenAlive(true);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);

        // draw first alive cells
        if (!initialized) {
            for (int i = 1; i < 49; i++) {
                for (int j = 1; j < 49; j++) {
                    if (j % 10 == 0 || i % 10 == 0) {
                        int x = (int) (cells[i][j].getX());
                        int y = (int) (cells[i][j].getY());
                        g.fillRect(x, y, 10, 10);
                        cells[i][j].setNextGenAlive(true);
                    }
                }
            }
            initialized = true;
        }

        // draw cells
        for (int i = 1; i < 49; i++) {
            for (int j = 1; j < 49; j++) {
                int x = (int) (cells[i][j].getX());
                int y = (int) (cells[i][j].getY());
                if (cells[i][j].isNextGenAlive()) {
                    g.setColor(Color.WHITE);
                    cells[i][j].setAlive(true);
                } else {
                    g.setColor(Color.BLACK);
                    cells[i][j].setAlive(false);
                }
                g.fillRect(x, y, 10, 10);
            }
        }

    }

    public static Cell[][] getCells() {
        return cells;
    }
}