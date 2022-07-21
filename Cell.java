import java.awt.Rectangle;

public class Cell extends Rectangle {
    private boolean alive = false;
    private static int width = 10;
    private static int height = 10;

    private int neighbours = 0;

    private static Cell[][] cells = Main.getCells();


    public Cell(int i, int j) {
        this.setBounds(10 * i, 10 * j, width, height);
    }
    
    public static void initializeCells() {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                cells[i][j] = new Cell(i, j);
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
}
