package src;

import java.util.ArrayList;

public class Bresenham {

    // Bresenham line algorithm
    public static ArrayList<Cell> findLine(Cell[][] grid, int lastCellX, int lastCellY, int cellX, int cellY) {
        ArrayList<Cell> line = new ArrayList<Cell>();

        int xDistance = Math.abs(cellX - lastCellX);
        int yDistance = Math.abs(cellY - lastCellY);

        int xDirection = lastCellX < cellX ? 1 : -1;
        int yDirection = lastCellY < cellY ? 1 : -1;

        int error = xDistance - yDistance;
        int doubleError;

        while (true) {
            line.add(grid[lastCellX][lastCellY]);

            if (lastCellX == cellX && lastCellY == cellY)
                break;

            doubleError = 2 * error;
            if (doubleError > -yDistance) {
                error = error - yDistance;
                lastCellX = lastCellX + xDirection;
            }

            if (doubleError < xDistance) {
                error = error + xDistance;
                lastCellY = lastCellY + yDirection;
            }
        }
        return line;
    }
}
