package src;
import java.awt.Color;
import java.awt.Rectangle;

public class Cell extends Rectangle {
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

    public Cell(int i, int j) {
        this.setBounds(cellWidth * i, cellHeight * j, cellWidth, cellHeight);
    }

    public static void initializeCells() {
        xGrids = Main.getMain().getWidth() / cellWidth;
        yGrids = Main.getMain().getHeight() / cellHeight;

        cells = new Cell[xGrids][yGrids];

        Main.setxGrids(xGrids);
        Main.setyGrids(yGrids);

        for (int i = 0; i < xGrids; i++) {
            for (int j = 0; j < yGrids; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public static void countNeighbours(Cell cell, int i, int j) {
        int neighbours = 0;

        // counts alive neighbours of every single cell
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

        // sets next gen cell alive or dead according to number of neighbours
        if (cell.alive) {
            if (neighbours <= 1 || neighbours >= 4) {
                cell.nextGenAlive = false;
            }
        } else {
            if (neighbours == 3) {
                cell.nextGenAlive = true;
            }
        }

        // change color for each cell
        if (!AdvancedOptions.isOneGenerationColor()) {
            CellColor.handleCellColor(cells[i][j]);
        } else {
            CellColor.handleGenerationColor(cells[i][j]);
        }
    }

    public static void initAliveCells() {

        switch (AdvancedOptions.getAliveCellMode()) {
            case Empty:
                break;
            case Line:
                for (int i = 1; i < xGrids - 1; i++) {
                    for (int j = 1; j < yGrids - 1; j++) {
                        if (j % AdvancedOptions.getLineDistance() == 0 || i % AdvancedOptions.getLineDistance() == 0) {
                            cells[i][j].nextGenAlive = true;
                            if (!AdvancedOptions.isOneGenerationColor()) {
                                CellColor.handleCellColor(cells[i][j]);
                            } else {
                                CellColor.handleGenerationColor(cells[i][j]);
                            }
                        }
                    }
                }
                break;
            case Random:
                for (int i = 1; i < xGrids - 1; i++) {
                    for (int j = 1; j < yGrids - 1; j++) {
                        cells[i][j].nextGenAlive = (Math.random() < AdvancedOptions.getPercOfAliveCells() / 100);
                        if (!AdvancedOptions.isOneGenerationColor()) {
                            CellColor.handleCellColor(cells[i][j]);
                        } else {
                            CellColor.handleGenerationColor(cells[i][j]);
                        }
                    }
                }
                break;
        }
    }

    // Creates 4 threads that count the neighbours of every cell
    public static void makeCountingThreads() {

        Threadmaker.runInThread(() -> {
            for (int i = 1; i < (xGrids / 2) - 1; i++) {
                for (int j = 1; j < (yGrids / 2) - 1; j++) {
                    countNeighbours(cells[i][j], i, j);
                }
            }
        });
        Threadmaker.runInThread(() -> {
            for (int i = (xGrids / 2) - 1; i < xGrids - 1; i++) {
                for (int j = 1; j < (yGrids / 2) - 1; j++) {
                    countNeighbours(cells[i][j], i, j);
                }
            }
        });
        Threadmaker.runInThread(() -> {
            for (int i = 1; i < (xGrids / 2) - 1; i++) {
                for (int j = (yGrids / 2) - 1; j < yGrids - 1; j++) {
                    countNeighbours(cells[i][j], i, j);
                }
            }
        });
        Threadmaker.runInThread(() -> {
            for (int i = (xGrids / 2) - 1; i < xGrids - 1; i++) {
                for (int j = (yGrids / 2) - 1; j < yGrids - 1; j++) {
                    countNeighbours(cells[i][j], i, j);
                }
            }
        });
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

    public boolean isLastGenAlive() {
        return lastGenAlive;
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
