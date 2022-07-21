import java.awt.Rectangle;

public class Cell extends Rectangle {
    private boolean alive = false;
    private boolean nextGenAlive = false;

    private static int cellWidth = 3;
    private static int cellHeight = 3;

    private int neighbours = 0;

    private static Cell[][] cells;


    public Cell(int i, int j) {
        this.setBounds(cellWidth * i, cellHeight * j, cellWidth, cellHeight);
    }
    
    public static void initializeCells() {
        int xGrids = Main.getPanelWidth() / cellWidth;
        int yGrids = Main.getPanelHeight() / cellHeight;
        cells = new Cell[xGrids][yGrids];

        for (int i = 0; i < xGrids; i++) {
            for (int j = 0; j < yGrids; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public static void countNeighbours(Cell cell, int xGrid, int yGrid) {
        int neighbours = 0;

        // counts alive neighbours of every single cell
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (cells[xGrid + i][yGrid + j].alive)
                    if (i != 0 || j != 0) {
                        neighbours++;
                    }
            }
        }

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
}
