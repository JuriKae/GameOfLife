import java.awt.Rectangle;

public class Cell extends Rectangle {
    private boolean alive = false;
    private boolean nextGenAlive = false;

    private static int cellWidth = 10;
    private static int cellHeight = 10;

    private static int xGrids;
    private static int yGrids;

    private static int finishedThreads;

    private int neighbours = 0;

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

    public static /* synchronized */ void countNeighbours(Cell cell, int xGrid, int yGrid) {
        int neighbours = 0;

        // counts alive neighbours of every single cell
        if (cells[xGrid - 1][yGrid - 1].isAlive())
            neighbours++;
        if (cells[xGrid][yGrid - 1].isAlive())
            neighbours++;
        if (cells[xGrid + 1][yGrid - 1].isAlive())
            neighbours++;
        if (cells[xGrid - 1][yGrid].isAlive())
            neighbours++;
        if (cells[xGrid + 1][yGrid].isAlive())
            neighbours++;
        if (cells[xGrid - 1][yGrid + 1].isAlive())
            neighbours++;
        if (cells[xGrid][yGrid + 1].isAlive())
            neighbours++;
        if (cells[xGrid + 1][yGrid + 1].isAlive())
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
    }

    // Creates 4 threads that count the neighbours of every cell
    public static void makeCountingThreads() {

        Threadmaker.runInThread(() -> {
            for (int i = 1; i < (xGrids / 2) - 1; i++) {
                for (int j = 1; j < (yGrids / 2) - 1; j++) {
                    countNeighbours(cells[i][j], i, j);
                }
            }
            finishedThreads++;
        });
        Threadmaker.runInThread(() -> {
            for (int i = (xGrids / 2) - 1; i < xGrids - 1; i++) {
                for (int j = 1; j < (yGrids / 2) - 1; j++) {
                    countNeighbours(cells[i][j], i, j);
                }
            }
            finishedThreads++;
        });
        Threadmaker.runInThread(() -> {
            for (int i = 1; i < (xGrids / 2) - 1; i++) {
                for (int j = (yGrids / 2) - 1; j < yGrids - 1; j++) {
                    countNeighbours(cells[i][j], i, j);
                }
            }
            finishedThreads++;
        });
        Threadmaker.runInThread(() -> {
            for (int i = (xGrids / 2) - 1; i < xGrids - 1; i++) {
                for (int j = (yGrids / 2) - 1; j < yGrids - 1; j++) {
                    countNeighbours(cells[i][j], i, j);
                }
            }
            finishedThreads++;
        });
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(int neighbours) {
        this.neighbours = neighbours;
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

    public static void setCells(Cell[][] cells) {
        Cell.cells = cells;
    }

    public static int getFinishedThreads() {
        return finishedThreads;
    }

    public static void setFinishedThreads(int finishedThreads) {
        Cell.finishedThreads = finishedThreads;
    }
}
