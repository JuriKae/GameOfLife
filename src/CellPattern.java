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
            default:
                break;
        }
    }

    public static void makeGlider(int xGrid, int yGrid) {
        // 3x3
        System.out.println("Making Glider.");
        Cell[][] cells = Cell.getCells();

        if (xGrid + 3 > Cell.getxGrids() || yGrid + 3 > Cell.getyGrids()) {
            return;
        }
    
        cells[xGrid][yGrid].setNextGenAlive(true);
        cells[xGrid + 1][yGrid].setNextGenAlive(true);
        cells[xGrid + 2][yGrid + 1].setNextGenAlive(true);
        cells[xGrid][yGrid + 1].setNextGenAlive(true);
        cells[xGrid][yGrid + 2].setNextGenAlive(true);
    }

    public static void makePulsar(int xGrid, int yGrid) {
        
    }
}
