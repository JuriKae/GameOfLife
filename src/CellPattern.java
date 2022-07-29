package src;

public class CellPattern {

    public static void createPattern(int xGrid, int yGrid) {
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
            default:
                break;
        }
    }

    public static void makeGlider(int xGrid, int yGrid) {
        // 3x3
        Cell[][] cells = Cell.getCells();

        if (xGrid + 3 > Cell.getxGrids() || yGrid + 3 > Cell.getyGrids()) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[xGrid + i][yGrid + j].setNextGenAlive(false);
            }
        }

        cells[xGrid][yGrid].setNextGenAlive(true);
        cells[xGrid + 1][yGrid].setNextGenAlive(true);
        cells[xGrid + 2][yGrid + 1].setNextGenAlive(true);
        cells[xGrid][yGrid + 1].setNextGenAlive(true);
        cells[xGrid][yGrid + 2].setNextGenAlive(true);
    }

    public static void makePulsar(int xGrid, int yGrid) {
        // 13x13
        Cell[][] cells = Cell.getCells();

        if (xGrid + 13 > Cell.getxGrids() || yGrid + 13 > Cell.getyGrids()) {
            return;
        }

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                cells[xGrid + i][yGrid + j].setNextGenAlive(false);
            }
        }

        cells[xGrid + 2][yGrid].setNextGenAlive(true);
        cells[xGrid + 3][yGrid].setNextGenAlive(true);
        cells[xGrid + 4][yGrid].setNextGenAlive(true);
        cells[xGrid + 8][yGrid].setNextGenAlive(true);
        cells[xGrid + 9][yGrid].setNextGenAlive(true);
        cells[xGrid + 10][yGrid].setNextGenAlive(true);
        cells[xGrid][yGrid + 2].setNextGenAlive(true);
        cells[xGrid + 5][yGrid + 2].setNextGenAlive(true);
        cells[xGrid + 7][yGrid + 2].setNextGenAlive(true);
        cells[xGrid + 12][yGrid + 2].setNextGenAlive(true);
        cells[xGrid][yGrid + 3].setNextGenAlive(true);
        cells[xGrid + 5][yGrid + 3].setNextGenAlive(true);
        cells[xGrid + 7][yGrid + 3].setNextGenAlive(true);
        cells[xGrid + 12][yGrid + 3].setNextGenAlive(true);
        cells[xGrid][yGrid + 4].setNextGenAlive(true);
        cells[xGrid + 5][yGrid + 4].setNextGenAlive(true);
        cells[xGrid + 7][yGrid + 4].setNextGenAlive(true);
        cells[xGrid + 12][yGrid + 4].setNextGenAlive(true);
        cells[xGrid + 2][yGrid + 5].setNextGenAlive(true);
        cells[xGrid + 3][yGrid + 5].setNextGenAlive(true);
        cells[xGrid + 4][yGrid + 5].setNextGenAlive(true);
        cells[xGrid + 8][yGrid + 5].setNextGenAlive(true);
        cells[xGrid + 9][yGrid + 5].setNextGenAlive(true);
        cells[xGrid + 10][yGrid + 5].setNextGenAlive(true);
        cells[xGrid + 2][yGrid + 7].setNextGenAlive(true);
        cells[xGrid + 3][yGrid + 7].setNextGenAlive(true);
        cells[xGrid + 4][yGrid + 7].setNextGenAlive(true);
        cells[xGrid + 8][yGrid + 7].setNextGenAlive(true);
        cells[xGrid + 9][yGrid + 7].setNextGenAlive(true);
        cells[xGrid + 10][yGrid + 7].setNextGenAlive(true);
        cells[xGrid][yGrid + 8].setNextGenAlive(true);
        cells[xGrid + 5][yGrid + 8].setNextGenAlive(true);
        cells[xGrid + 7][yGrid + 8].setNextGenAlive(true);
        cells[xGrid + 12][yGrid + 8].setNextGenAlive(true);
        cells[xGrid][yGrid + 9].setNextGenAlive(true);
        cells[xGrid + 5][yGrid + 9].setNextGenAlive(true);
        cells[xGrid + 7][yGrid + 9].setNextGenAlive(true);
        cells[xGrid + 12][yGrid + 9].setNextGenAlive(true);
        cells[xGrid][yGrid + 10].setNextGenAlive(true);
        cells[xGrid + 5][yGrid + 10].setNextGenAlive(true);
        cells[xGrid + 7][yGrid + 10].setNextGenAlive(true);
        cells[xGrid + 12][yGrid + 10].setNextGenAlive(true);
        cells[xGrid + 2][yGrid + 12].setNextGenAlive(true);
        cells[xGrid + 3][yGrid + 12].setNextGenAlive(true);
        cells[xGrid + 4][yGrid + 12].setNextGenAlive(true);
        cells[xGrid + 8][yGrid + 12].setNextGenAlive(true);
        cells[xGrid + 9][yGrid + 12].setNextGenAlive(true);
        cells[xGrid + 10][yGrid + 12].setNextGenAlive(true);
    }

    public static void makeLWSS(int xGrid, int yGrid) {
        // 5x4
        Cell[][] cells = Cell.getCells();

        if (xGrid + 5 > Cell.getxGrids() || yGrid + 4 > Cell.getyGrids()) {
            return;
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                cells[xGrid + i][yGrid + j].setNextGenAlive(false);
            }
        }

        cells[xGrid + 1][yGrid].setNextGenAlive(true);
        cells[xGrid + 2][yGrid].setNextGenAlive(true);
        cells[xGrid + 3][yGrid].setNextGenAlive(true);
        cells[xGrid + 4][yGrid].setNextGenAlive(true);
        cells[xGrid][yGrid + 1].setNextGenAlive(true);
        cells[xGrid + 4][yGrid + 1].setNextGenAlive(true);
        cells[xGrid + 4][yGrid + 2].setNextGenAlive(true);
        cells[xGrid][yGrid + 3].setNextGenAlive(true);
        cells[xGrid + 3][yGrid + 3].setNextGenAlive(true);
    }

    public static void makeHWSS(int xGrid, int yGrid) {
        // 7x5
        Cell[][] cells = Cell.getCells();

        if (xGrid + 7 > Cell.getxGrids() || yGrid + 5 > Cell.getyGrids()) {
            return;
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                cells[xGrid + i][yGrid + j].setNextGenAlive(false);
            }
        }

        cells[xGrid + 1][yGrid].setNextGenAlive(true);
        cells[xGrid + 2][yGrid].setNextGenAlive(true);
        cells[xGrid + 3][yGrid].setNextGenAlive(true);
        cells[xGrid + 4][yGrid].setNextGenAlive(true);
        cells[xGrid + 5][yGrid].setNextGenAlive(true);
        cells[xGrid + 6][yGrid].setNextGenAlive(true);
        cells[xGrid][yGrid + 1].setNextGenAlive(true);
        cells[xGrid + 6][yGrid + 1].setNextGenAlive(true);
        cells[xGrid + 6][yGrid + 2].setNextGenAlive(true);
        cells[xGrid][yGrid + 3].setNextGenAlive(true);
        cells[xGrid + 5][yGrid + 3].setNextGenAlive(true);
        cells[xGrid + 2][yGrid + 4].setNextGenAlive(true);
        cells[xGrid + 3][yGrid + 4].setNextGenAlive(true);
    }
}
