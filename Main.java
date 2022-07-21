import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

    private static JFrame frame;

    private static int width = 516;
    private static int height = 539;

    private static Cell[][] cells = new Cell[500 / 10][500 / 10];
    private static Cell[][] aliveCells = new Cell[500 / 10][500 / 10];
    private static Cell[][] deadCells = new Cell[500 / 10][500 / 10];

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

    public static void countNeighbours(Cell cell, int i, int j) {
        int neighbours = 0;

        // TO-DO: nested for loop
        if (cells[i - 1][j - 1].isAlive())
            neighbours++;
        if (cells[i][j - 1].isAlive())
            neighbours++;
        if (cells[i + 1][j - 1].isAlive())
            neighbours++;
        if (cells[i - 1][j].isAlive())
            neighbours++;
        if (cells[i + 1][j].isAlive())
            neighbours++;
        if (cells[i - 1][j + 1].isAlive())
            neighbours++;
        if (cells[i][j + 1].isAlive())
            neighbours++;
        if (cells[i + 1][j + 1].isAlive())
            neighbours++;

        if (cell.isAlive()) {
            if (neighbours <= 1 || neighbours >= 4)
                cell.setAlive(false);
            deadCells[i][j] = cell;
        } else {
            if (neighbours == 3)
                cell.setAlive(true);
            aliveCells[i][j] = cell;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);

        // draw first alive cells
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (j % 10 == 0 || i % 10 == 0) {
                    int x = (int) (cells[i][j].getX());
                    int y = (int) (cells[i][j].getY());
                    g.fillRect(x, y, 10, 10);
                    cells[i][j].setAlive(true);
                }
            }
        }

        // draw cells
        for (int i = 1; i < 49; i++) {
            for (int j = 1; j < 49; j++) {
                int x = (int) (cells[i][j].getX());
                int y = (int) (cells[i][j].getY());
                if (cells[i][j].isAlive()) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x, y, 10, 10);
            }
        }

    }

    public static Cell[][] getCells() {
        return cells;
    }
}