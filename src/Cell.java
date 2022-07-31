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
        
        for (int i = 0; i < xGrids; i++) {
            for (int j = 0; j < yGrids; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }

        initAliveCells();
        Main.setInitialized(true);
    }

    public static void initAliveCells() {

        switch (AdvancedOptions.getAliveCellMode()) {
            case Empty:
                break;
            case Line:
                for (int i = 1; i < xGrids - 1; i++) {
                    for (int j = 1; j < yGrids - 1; j++) {
                        // sets cell alive in lines; line distance can be changed in the options
                        if (j % AdvancedOptions.getLineDistance() == 0 || i % AdvancedOptions.getLineDistance() == 0) {
                            cells[i][j].alive = true;
                            cells[i][j].nextGenAlive = true;
                        }
                    }
                }
                break;
            case Random:
                for (int i = 0; i < xGrids; i++) {
                    for (int j = 0; j < yGrids; j++) {
                        // sets cell alive randomly; % of alive cells can be changed in options
                        if (Math.random() < AdvancedOptions.getPercOfAliveCells() / 100) {
                            cells[i][j].alive = true;
                            cells[i][j].nextGenAlive = true;
                        }
                    }
                }
                break;
        }
        CellColor.callChangeColorFunction();
    }

    public static void countNeighbours() {
        
        for (int i = 0; i < xGrids; i++) {
            for (int j = 0; j < yGrids; j++) {
                int neighbours = 0;

                neighbours += cells[(i + xGrids - 1) % xGrids][(j + yGrids - 1) % yGrids].alive ? 1 : 0;
                neighbours += cells[(i + xGrids + 0) % xGrids][(j + yGrids - 1) % yGrids].alive ? 1 : 0;
                neighbours += cells[(i + xGrids + 1) % xGrids][(j + yGrids - 1) % yGrids].alive ? 1 : 0;
                neighbours += cells[(i + xGrids - 1) % xGrids][(j + yGrids + 0) % yGrids].alive ? 1 : 0;
                neighbours += cells[(i + xGrids + 1) % xGrids][(j + yGrids + 0) % yGrids].alive ? 1 : 0;
                neighbours += cells[(i + xGrids - 1) % xGrids][(j + yGrids + 1) % yGrids].alive ? 1 : 0;
                neighbours += cells[(i + xGrids + 0) % xGrids][(j + yGrids + 1) % yGrids].alive ? 1 : 0;
                neighbours += cells[(i + xGrids + 1) % xGrids][(j + yGrids + 1) % yGrids].alive ? 1 : 0;

                // sets next gen cell alive or dead according to number of neighbours
                if (!cells[i][j].alive) {
                    cells[i][j].nextGenAlive = neighbours == 3 ? true : false;
                } else {
                    cells[i][j].nextGenAlive = neighbours <= 1 || neighbours >= 4 ? false : true;
                }
            }
        }
        CellColor.callChangeColorFunction();
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
