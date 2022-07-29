package src;

public class CellPattern {

    private static int totalXGrids;
    private static int totalYGrids;

    private static int inBoundsXGrid;
    private static int inBoundsYGrid;

    public static void createPattern(int xGrid, int yGrid) {

        // calculate grid location, so that pattern can be set next to a wall
        totalXGrids = Cell.getxGrids();
        totalYGrids = Cell.getyGrids();

        inBoundsXGrid = (totalXGrids + xGrid) % totalXGrids;
        inBoundsYGrid = (totalYGrids + yGrid) % totalYGrids;

        switch (PatternPanel.getChosenPattern()) {
            case Glider:
                makeGlider(xGrid, yGrid);
                break;
            case Pulsar:
                makePulsar(xGrid, yGrid);
                break;
            case LWSS:
                makeLWSS(xGrid, yGrid);
                break;
            case HWSS:
                makeHWSS(xGrid, yGrid);
                break;
            case PENTDADECA:
                makePentaDeca(xGrid, yGrid);
            default:
                break;
        }
    }

    public static void makeGlider(int xGrid, int yGrid) {
        // 3x3
        Cell[][] cells = Cell.getCells();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[(inBoundsXGrid + i) % totalXGrids][(inBoundsYGrid + j) % totalYGrids].setNextGenAlive(false);
            }
        }

        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 1) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 2) % totalXGrids][(inBoundsYGrid + 1) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 1) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 2) % totalYGrids].setNextGenAlive(true);
    }

    public static void makePulsar(int xGrid, int yGrid) {
        // 15x15
        Cell[][] cells = Cell.getCells();

        for (int i = -1; i < 14; i++) {
            for (int j = -1; j < 14; j++) {
                cells[(inBoundsXGrid + i) % totalXGrids][(inBoundsYGrid + j) % totalYGrids].setNextGenAlive(false);
            }
        }

        cells[(inBoundsXGrid + 2) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 3) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 4) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 8) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 9) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 10) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 2) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 5) % totalXGrids][(inBoundsYGrid + 2) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 7) % totalXGrids][(inBoundsYGrid + 2) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 12) % totalXGrids][(inBoundsYGrid + 2) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 3) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 5) % totalXGrids][(inBoundsYGrid + 3) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 7) % totalXGrids][(inBoundsYGrid + 3) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 12) % totalXGrids][(inBoundsYGrid + 3) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 4) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 5) % totalXGrids][(inBoundsYGrid + 4) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 7) % totalXGrids][(inBoundsYGrid + 4) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 12) % totalXGrids][(inBoundsYGrid + 4) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 2) % totalXGrids][(inBoundsYGrid + 5) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 3) % totalXGrids][(inBoundsYGrid + 5) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 4) % totalXGrids][(inBoundsYGrid + 5) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 8) % totalXGrids][(inBoundsYGrid + 5) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 9) % totalXGrids][(inBoundsYGrid + 5) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 10) % totalXGrids][(inBoundsYGrid + 5) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 2) % totalXGrids][(inBoundsYGrid + 7) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 3) % totalXGrids][(inBoundsYGrid + 7) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 4) % totalXGrids][(inBoundsYGrid + 7) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 8) % totalXGrids][(inBoundsYGrid + 7) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 9) % totalXGrids][(inBoundsYGrid + 7) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 10) % totalXGrids][(inBoundsYGrid + 7) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 8) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 5) % totalXGrids][(inBoundsYGrid + 8) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 7) % totalXGrids][(inBoundsYGrid + 8) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 12) % totalXGrids][(inBoundsYGrid + 8) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 9) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 5) % totalXGrids][(inBoundsYGrid + 9) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 7) % totalXGrids][(inBoundsYGrid + 9) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 12) % totalXGrids][(inBoundsYGrid + 9) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 10) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 5) % totalXGrids][(inBoundsYGrid + 10) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 7) % totalXGrids][(inBoundsYGrid + 10) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 12) % totalXGrids][(inBoundsYGrid + 10) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 2) % totalXGrids][(inBoundsYGrid + 12) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 3) % totalXGrids][(inBoundsYGrid + 12) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 4) % totalXGrids][(inBoundsYGrid + 12) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 8) % totalXGrids][(inBoundsYGrid + 12) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 9) % totalXGrids][(inBoundsYGrid + 12) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 10) % totalXGrids][(inBoundsYGrid + 12) % totalYGrids].setNextGenAlive(true);
    }

    public static void makeLWSS(int xGrid, int yGrid) {
        // 5x4
        Cell[][] cells = Cell.getCells();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                cells[(inBoundsXGrid + i) % totalXGrids][(inBoundsYGrid + j) % totalYGrids].setNextGenAlive(false);
            }
        }

        cells[(inBoundsXGrid + 1) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 2) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 3) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 4) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 1) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 4) % totalXGrids][(inBoundsYGrid + 1) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 4) % totalXGrids][(inBoundsYGrid + 2) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 3) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 3) % totalXGrids][(inBoundsYGrid + 3) % totalYGrids].setNextGenAlive(true);
    }

    public static void makeHWSS(int xGrid, int yGrid) {
        // 7x5
        Cell[][] cells = Cell.getCells();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                cells[(inBoundsXGrid + i) % totalXGrids][(inBoundsYGrid + j) % totalXGrids].setNextGenAlive(false);
            }
        }

        cells[(inBoundsXGrid + 1) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 2) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 3) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 4) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 5) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 6) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 1) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 6) % totalXGrids][(inBoundsYGrid + 1) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 6) % totalXGrids][(inBoundsYGrid + 2) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 3) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 5) % totalXGrids][(inBoundsYGrid + 3) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 2) % totalXGrids][(inBoundsYGrid + 4) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 3) % totalXGrids][(inBoundsYGrid + 4) % totalYGrids].setNextGenAlive(true);
    }

    public static void makePentaDeca(int inBoundsXGrid, int yGrid) {
        // 16x9
        Cell[][] cells = Cell.getCells();

        for (int i = -3; i < 13; i++) {
            for (int j = 0; j < 9; j++) {
                cells[(inBoundsXGrid + i) % totalXGrids][(inBoundsYGrid + j) % totalYGrids].setNextGenAlive(false);
            }
        }

        cells[(inBoundsXGrid + 4) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 5) % totalXGrids][(inBoundsYGrid + 0) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 2) % totalXGrids][(inBoundsYGrid + 1) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 7) % totalXGrids][(inBoundsYGrid + 1) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 1) % totalXGrids][(inBoundsYGrid + 2) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 8) % totalXGrids][(inBoundsYGrid + 2) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 3) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 9) % totalXGrids][(inBoundsYGrid + 3) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 4) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 9) % totalXGrids][(inBoundsYGrid + 4) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 0) % totalXGrids][(inBoundsYGrid + 5) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 9) % totalXGrids][(inBoundsYGrid + 5) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 1) % totalXGrids][(inBoundsYGrid + 6) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 8) % totalXGrids][(inBoundsYGrid + 6) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 2) % totalXGrids][(inBoundsYGrid + 7) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 7) % totalXGrids][(inBoundsYGrid + 7) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 4) % totalXGrids][(inBoundsYGrid + 8) % totalYGrids].setNextGenAlive(true);
        cells[(inBoundsXGrid + 5) % totalXGrids][(inBoundsYGrid + 8) % totalYGrids].setNextGenAlive(true);
    }
}
