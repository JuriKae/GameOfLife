package src;

import java.awt.Color;

public class Cell {
    private boolean alive = false;
    private boolean nextGenAlive = false;
    private boolean lastGenAlive = false;

    private static int cellWidth = 6;
    private static int cellHeight = 6;

    private Color aliveColor = Color.WHITE;
    private Color deadColor = Color.BLACK;

    private static int xGrids;
    private static int yGrids;

    private static Cell[][] cells;

    private int x, y;

    public Cell(int i, int j) {
        this.x = i * cellWidth;
        this.y = j * cellHeight;
    }

    // Standard getters to maintain compatibility with MouseCellListener
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return cellWidth;
    }

    public int getHeight() {
        return cellHeight;
    }

    public double getCenterX() {
        return x + cellWidth / 2.0;
    }

    public double getCenterY() {
        return y + cellHeight / 2.0;
    }

    public static void initializeCells(int mainWidth, int mainHeight) {
        xGrids = mainWidth / cellWidth;
        yGrids = mainHeight / cellHeight;
        cells = new Cell[xGrids][yGrids];

        for (int i = 0; i < xGrids; i++) {
            for (int j = 0; j < yGrids; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        initAliveCells();
    }

    public static void initAliveCells() {
        switch (AdvancedOptions.getAliveCellMode()) {
            case Empty:
                break;
            case Line:
                int lineDistance = AdvancedOptions.getLineDistance();
                for (int i = 1; i < xGrids - 1; i++) {
                    for (int j = 1; j < yGrids - 1; j++) {
                        // sets cell alive in lines; line distance can be changed in the options
                        if (j % lineDistance == 0 || i % lineDistance == 0) {
                            cells[i][j].alive = true;
                        }
                    }
                }
                break;
            case Random:
                float percOfAliveCells = AdvancedOptions.getPercOfAliveCells();
                for (int i = 0; i < xGrids; i++) {
                    for (int j = 0; j < yGrids; j++) {
                        // sets cell alive randomly; % of alive cells can be changed in options
                        cells[i][j].alive = (Math.random() < percOfAliveCells / 100);
                    }
                }
                break;
        }
        CellColor.callChangeColorFunction();
    }

    public static void countNeighbors() {
        for (int i = 0; i < xGrids; i++) {
            // Pre-calculate neighbor X indices once per column
            int left = (i == 0) ? xGrids - 1 : i - 1;
            int right = (i == xGrids - 1) ? 0 : i + 1;

            for (int j = 0; j < yGrids; j++) {
                // Pre-calculate neighbor Y indices once per cell
                int top = (j == 0) ? yGrids - 1 : j - 1;
                int bottom = (j == yGrids - 1) ? 0 : j + 1;

                int neighbors = 0;

                // Count number of alive neighbors
                if (cells[left][top].alive)
                    neighbors++;
                if (cells[i][top].alive)
                    neighbors++;
                if (cells[right][top].alive)
                    neighbors++;
                if (cells[left][j].alive)
                    neighbors++;
                if (cells[right][j].alive)
                    neighbors++;
                if (cells[left][bottom].alive)
                    neighbors++;
                if (cells[i][bottom].alive)
                    neighbors++;
                if (cells[right][bottom].alive)
                    neighbors++;

                // sets next gen cell alive or dead according to number of neighbors
                Cell current = cells[i][j];
                if (!current.alive) {
                    current.nextGenAlive = (neighbors == 3);
                } else {
                    current.nextGenAlive = (neighbors == 2 || neighbors == 3);
                }
            }
        }
    }

    public static void takeAStepBack() {
        for (int i = 0; i < xGrids; i++) {
            for (int j = 0; j < yGrids; j++) {
                cells[i][j].alive = cells[i][j].lastGenAlive;
            }
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isNextGenAlive() {
        return nextGenAlive;
    }

    public void setNextGenAlive(boolean nextGenAlive) {
        this.nextGenAlive = nextGenAlive;
    }

    public static int getCellWidth() {
        return cellWidth;
    }

    public static void setCellWidth(int cellWidth) {
        Cell.cellWidth = cellWidth;
    }

    public static int getCellHeight() {
        return cellHeight;
    }

    public static void setCellHeight(int cellHeight) {
        Cell.cellHeight = cellHeight;
    }

    public static Cell[][] getCells() {
        return cells;
    }

    public Color getAliveColor() {
        return aliveColor;
    }

    public Color getDeadColor() {
        return deadColor;
    }

    public static int getxGrids() {
        return xGrids;
    }

    public static int getyGrids() {
        return yGrids;
    }

    public void setLastGenAlive(boolean lastGenAlive) {
        this.lastGenAlive = lastGenAlive;
    }

    public void setAliveColor(Color aliveColor) {
        this.aliveColor = aliveColor;
    }

    public void setDeadColor(Color deadColor) {
        this.deadColor = deadColor;
    }
}
