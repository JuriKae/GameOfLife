package src;

public class CellPattern {

    private static int totalXGrids;
    private static int totalYGrids;

    public static void createPattern(int xGrid, int yGrid) {

        totalXGrids = Cell.getxGrids();
        totalYGrids = Cell.getyGrids();
        
        // calculate grid location, so that pattern can be set next to a wall
        int inBoundsXGrid = (totalXGrids + xGrid) % totalXGrids;
        int inBoundsYGrid = (totalYGrids + yGrid) % totalYGrids;

        switch (PatternPanel.getChosenPattern()) {
            case Glider:
                makeGlider(inBoundsXGrid, inBoundsYGrid);
                break;
            case LWSS:
                makeLWSS(inBoundsXGrid, inBoundsYGrid);
                break;
            case HWSS:
                makeHWSS(inBoundsXGrid, inBoundsYGrid);
                break;
            case Pulsar:
                makePulsar(inBoundsXGrid, inBoundsYGrid);
                break;
            case Pentadeca:
                makePentaDeca(inBoundsXGrid, inBoundsYGrid);
                break;
            case Playbutton:
                makePlayButton(inBoundsXGrid, inBoundsYGrid);
                break;
            case GliderCannon:
                makeGliderCannon(inBoundsXGrid, inBoundsYGrid);
                break;
            default:
                break;
        }
    }

    public static void makeGlider(int x, int y)  {
        // 3x3
        Cell[][] cells = Cell.getCells();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[(x + i) % totalXGrids][(y + j) % totalYGrids].setAlive(false);
            }
        }

        cells[(x + 0) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 1) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 2) % totalXGrids][(y + 1) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 1) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);

        cells[(x + 0) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 1) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 2) % totalXGrids][(y + 1) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 1) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
    }

    public static void makeLWSS(int x, int y) {
        // 5x4
        Cell[][] cells = Cell.getCells();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                cells[(x + i) % totalXGrids][(y + j) % totalYGrids].setAlive(false);
            }
        }

        cells[(x + 1) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 2) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 3) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 1) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 1) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 3) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);

        cells[(x + 1) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 2) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 3) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 1) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 1) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 3) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
    }

    public static void makeHWSS(int x, int y) {
        // 7x5
        Cell[][] cells = Cell.getCells();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                cells[(x + i) % totalXGrids][(y + j) % totalXGrids].setAlive(false);
            }
        }

        cells[(x + 1) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 2) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 3) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 5) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 6) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 1) % totalYGrids].setAlive(true);
        cells[(x + 6) % totalXGrids][(y + 1) % totalYGrids].setAlive(true);
        cells[(x + 6) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 5) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 2) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 3) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);

        cells[(x + 1) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 2) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 3) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 5) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 6) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 1) % totalYGrids].setNextGenAlive(true);
        cells[(x + 6) % totalXGrids][(y + 1) % totalYGrids].setNextGenAlive(true);
        cells[(x + 6) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 5) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 2) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 3) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
    }

    public static void makePulsar(int x, int y) {
        // 15x15
        Cell[][] cells = Cell.getCells();

        for (int i = -1; i < 14; i++) {
            for (int j = -1; j < 14; j++) {
                cells[(x + i) % totalXGrids][(y + j) % totalYGrids].setNextGenAlive(false);
            }
        }

        cells[(x + 2) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 3) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 8) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 9) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 10) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 5) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 7) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 12) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 5) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 7) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 12) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 5) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 7) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 12) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 2) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 3) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 8) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 9) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 10) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 2) % totalXGrids][(y + 7) % totalYGrids].setAlive(true);
        cells[(x + 3) % totalXGrids][(y + 7) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 7) % totalYGrids].setAlive(true);
        cells[(x + 8) % totalXGrids][(y + 7) % totalYGrids].setAlive(true);
        cells[(x + 9) % totalXGrids][(y + 7) % totalYGrids].setAlive(true);
        cells[(x + 10) % totalXGrids][(y + 7) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);
        cells[(x + 5) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);
        cells[(x + 7) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);
        cells[(x + 12) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 9) % totalYGrids].setAlive(true);
        cells[(x + 5) % totalXGrids][(y + 9) % totalYGrids].setAlive(true);
        cells[(x + 7) % totalXGrids][(y + 9) % totalYGrids].setAlive(true);
        cells[(x + 12) % totalXGrids][(y + 9) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 10) % totalYGrids].setAlive(true);
        cells[(x + 5) % totalXGrids][(y + 10) % totalYGrids].setAlive(true);
        cells[(x + 7) % totalXGrids][(y + 10) % totalYGrids].setAlive(true);
        cells[(x + 12) % totalXGrids][(y + 10) % totalYGrids].setAlive(true);
        cells[(x + 2) % totalXGrids][(y + 12) % totalYGrids].setAlive(true);
        cells[(x + 3) % totalXGrids][(y + 12) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 12) % totalYGrids].setAlive(true);
        cells[(x + 8) % totalXGrids][(y + 12) % totalYGrids].setAlive(true);
        cells[(x + 9) % totalXGrids][(y + 12) % totalYGrids].setAlive(true);
        cells[(x + 10) % totalXGrids][(y + 12) % totalYGrids].setAlive(true);

        cells[(x + 2) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 3) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 8) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 9) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 10) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 5) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 7) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 12) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 5) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 7) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 12) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 5) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 7) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 12) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 2) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 3) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 8) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 9) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 10) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 2) % totalXGrids][(y + 7) % totalYGrids].setNextGenAlive(true);
        cells[(x + 3) % totalXGrids][(y + 7) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 7) % totalYGrids].setNextGenAlive(true);
        cells[(x + 8) % totalXGrids][(y + 7) % totalYGrids].setNextGenAlive(true);
        cells[(x + 9) % totalXGrids][(y + 7) % totalYGrids].setNextGenAlive(true);
        cells[(x + 10) % totalXGrids][(y + 7) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
        cells[(x + 5) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
        cells[(x + 7) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
        cells[(x + 12) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 9) % totalYGrids].setNextGenAlive(true);
        cells[(x + 5) % totalXGrids][(y + 9) % totalYGrids].setNextGenAlive(true);
        cells[(x + 7) % totalXGrids][(y + 9) % totalYGrids].setNextGenAlive(true);
        cells[(x + 12) % totalXGrids][(y + 9) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 10) % totalYGrids].setNextGenAlive(true);
        cells[(x + 5) % totalXGrids][(y + 10) % totalYGrids].setNextGenAlive(true);
        cells[(x + 7) % totalXGrids][(y + 10) % totalYGrids].setNextGenAlive(true);
        cells[(x + 12) % totalXGrids][(y + 10) % totalYGrids].setNextGenAlive(true);
        cells[(x + 2) % totalXGrids][(y + 12) % totalYGrids].setNextGenAlive(true);
        cells[(x + 3) % totalXGrids][(y + 12) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 12) % totalYGrids].setNextGenAlive(true);
        cells[(x + 8) % totalXGrids][(y + 12) % totalYGrids].setNextGenAlive(true);
        cells[(x + 9) % totalXGrids][(y + 12) % totalYGrids].setNextGenAlive(true);
        cells[(x + 10) % totalXGrids][(y + 12) % totalYGrids].setNextGenAlive(true);
    }

    public static void makePentaDeca(int x, int y) {
        // 16x9
        Cell[][] cells = Cell.getCells();

        for (int i = -3; i < 13; i++) {
            for (int j = 0; j < 9; j++) {
                cells[(x + i) % totalXGrids][(y + j) % totalYGrids].setAlive(false);
            }
        }

        cells[(x + 4) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 5) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 2) % totalXGrids][(y + 1) % totalYGrids].setAlive(true);
        cells[(x + 7) % totalXGrids][(y + 1) % totalYGrids].setAlive(true);
        cells[(x + 1) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 8) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 9) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 9) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 9) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 1) % totalXGrids][(y + 6) % totalYGrids].setAlive(true);
        cells[(x + 8) % totalXGrids][(y + 6) % totalYGrids].setAlive(true);
        cells[(x + 2) % totalXGrids][(y + 7) % totalYGrids].setAlive(true);
        cells[(x + 7) % totalXGrids][(y + 7) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);
        cells[(x + 5) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);

        cells[(x + 4) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 5) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 2) % totalXGrids][(y + 1) % totalYGrids].setNextGenAlive(true);
        cells[(x + 7) % totalXGrids][(y + 1) % totalYGrids].setNextGenAlive(true);
        cells[(x + 1) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 8) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 9) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 9) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 9) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 1) % totalXGrids][(y + 6) % totalYGrids].setNextGenAlive(true);
        cells[(x + 8) % totalXGrids][(y + 6) % totalYGrids].setNextGenAlive(true);
        cells[(x + 2) % totalXGrids][(y + 7) % totalYGrids].setNextGenAlive(true);
        cells[(x + 7) % totalXGrids][(y + 7) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
        cells[(x + 5) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
    }

    public static void makePlayButton(int x, int y) {
        // 15x9
        Cell[][] cells = Cell.getCells();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 9; j++) {
                cells[(x + i) % totalXGrids][(y + j) % totalYGrids].setAlive(false);
            }
        }

        cells[(x + 1) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 2) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 3) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 11) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 12) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 13) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 1) % totalYGrids].setAlive(true);
        cells[(x + 14) % totalXGrids][(y + 1) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 10) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 14) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 3) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 10) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 11) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 14) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 2) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 3) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 10) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 11) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 12) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 14) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 3) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 10) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 11) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 14) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 6) % totalYGrids].setAlive(true);
        cells[(x + 4) % totalXGrids][(y + 6) % totalYGrids].setAlive(true);
        cells[(x + 10) % totalXGrids][(y + 6) % totalYGrids].setAlive(true);
        cells[(x + 14) % totalXGrids][(y + 6) % totalYGrids].setAlive(true);
        cells[(x + 0) % totalXGrids][(y + 7) % totalYGrids].setAlive(true);
        cells[(x + 14) % totalXGrids][(y + 7) % totalYGrids].setAlive(true);
        cells[(x + 1) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);
        cells[(x + 2) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);
        cells[(x + 3) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);
        cells[(x + 11) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);
        cells[(x + 12) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);
        cells[(x + 13) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);

        cells[(x + 1) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 2) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 3) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 11) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 12) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 13) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 1) % totalYGrids].setNextGenAlive(true);
        cells[(x + 14) % totalXGrids][(y + 1) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 10) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 14) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 3) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 10) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 11) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 14) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 2) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 3) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 10) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 11) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 12) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 14) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 3) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 10) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 11) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 14) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 6) % totalYGrids].setNextGenAlive(true);
        cells[(x + 4) % totalXGrids][(y + 6) % totalYGrids].setNextGenAlive(true);
        cells[(x + 10) % totalXGrids][(y + 6) % totalYGrids].setNextGenAlive(true);
        cells[(x + 14) % totalXGrids][(y + 6) % totalYGrids].setNextGenAlive(true);
        cells[(x + 0) % totalXGrids][(y + 7) % totalYGrids].setNextGenAlive(true);
        cells[(x + 14) % totalXGrids][(y + 7) % totalYGrids].setNextGenAlive(true);
        cells[(x + 1) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
        cells[(x + 2) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
        cells[(x + 3) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
        cells[(x + 11) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
        cells[(x + 12) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
        cells[(x + 13) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
    }

    public static void makeGliderCannon(int x, int y) {
        // 36x9
        Cell[][] cells = Cell.getCells();

        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 9; j++) {
                cells[(x + i) % totalXGrids][(y + j) % totalYGrids].setAlive(false);
            }
        }

        cells[(x + 24) % totalXGrids][(y + 0) % totalYGrids].setAlive(true);
        cells[(x + 22) % totalXGrids][(y + 1) % totalYGrids].setAlive(true);
        cells[(x + 24) % totalXGrids][(y + 1) % totalYGrids].setAlive(true);
        cells[(x + 12) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 13) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 20) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 21) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 34) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 35) % totalXGrids][(y + 2) % totalYGrids].setAlive(true);
        cells[(x + 11) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 15) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 20) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 21) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 34) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 35) % totalXGrids][(y + 3) % totalYGrids].setAlive(true);
        cells[(x + 00) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 01) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 10) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 16) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 20) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 21) % totalXGrids][(y + 4) % totalYGrids].setAlive(true);
        cells[(x + 00) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 01) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 10) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 14) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 16) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 17) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 22) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 24) % totalXGrids][(y + 5) % totalYGrids].setAlive(true);
        cells[(x + 10) % totalXGrids][(y + 6) % totalYGrids].setAlive(true);
        cells[(x + 16) % totalXGrids][(y + 6) % totalYGrids].setAlive(true);
        cells[(x + 24) % totalXGrids][(y + 6) % totalYGrids].setAlive(true);
        cells[(x + 11) % totalXGrids][(y + 7) % totalYGrids].setAlive(true);
        cells[(x + 15) % totalXGrids][(y + 7) % totalYGrids].setAlive(true);
        cells[(x + 12) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);
        cells[(x + 13) % totalXGrids][(y + 8) % totalYGrids].setAlive(true);
        
        cells[(x + 24) % totalXGrids][(y + 0) % totalYGrids].setNextGenAlive(true);
        cells[(x + 22) % totalXGrids][(y + 1) % totalYGrids].setNextGenAlive(true);
        cells[(x + 24) % totalXGrids][(y + 1) % totalYGrids].setNextGenAlive(true);
        cells[(x + 12) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 13) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 20) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 21) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 34) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 35) % totalXGrids][(y + 2) % totalYGrids].setNextGenAlive(true);
        cells[(x + 11) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 15) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 20) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 21) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 34) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 35) % totalXGrids][(y + 3) % totalYGrids].setNextGenAlive(true);
        cells[(x + 00) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 01) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 10) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 16) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 20) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 21) % totalXGrids][(y + 4) % totalYGrids].setNextGenAlive(true);
        cells[(x + 00) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 01) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 10) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 14) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 16) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 17) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 22) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 24) % totalXGrids][(y + 5) % totalYGrids].setNextGenAlive(true);
        cells[(x + 10) % totalXGrids][(y + 6) % totalYGrids].setNextGenAlive(true);
        cells[(x + 16) % totalXGrids][(y + 6) % totalYGrids].setNextGenAlive(true);
        cells[(x + 24) % totalXGrids][(y + 6) % totalYGrids].setNextGenAlive(true);
        cells[(x + 11) % totalXGrids][(y + 7) % totalYGrids].setNextGenAlive(true);
        cells[(x + 15) % totalXGrids][(y + 7) % totalYGrids].setNextGenAlive(true);
        cells[(x + 12) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
        cells[(x + 13) % totalXGrids][(y + 8) % totalYGrids].setNextGenAlive(true);
    }
}
