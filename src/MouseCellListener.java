package src;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.SwingUtilities;

public class MouseCellListener extends MouseAdapter {

    private double xDifference;
    private double yDifference;

    private static boolean hasZoomed;

    private static boolean paintedWithMouse;

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        xDifference = (Cell.getCellWidth() * Main.getZoomFactor());
        yDifference = (Cell.getCellHeight() * Main.getZoomFactor());

        // // converts x and y to correct value, even when zoomed in
        int x = (int) (e.getX() / xDifference - (Main.getxOffset() / xDifference));
        int y = (int) (e.getY() / yDifference - (Main.getyOffset() / yDifference));

        if (x < 0 || x >= Cell.getxGrids() || y < 0 || y >= Cell.getyGrids()) {
            return;
        }

        // if a Pattern is selected, return after drawing it
        if (PatternPanel.isPattern() && SwingUtilities.isLeftMouseButton(e)) {
            CellPattern.createPattern(x, y);
            Main.getMain().repaint();
            return;
        }

        if (x < 0 || x > Cell.getxGrids() || y < 0 || y > Cell.getyGrids()) {
            return;
        }

        if (SwingUtilities.isLeftMouseButton(e)) {
            Cell.getCells()[x][y].setAlive(true);
            Cell.getCells()[x][y].setNextGenAlive(true);
            Main.getMain().paintWithMouse(Main.getMain().getGraphics(), x, y, Cell.getCells()[x][y], true, false);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            Cell.getCells()[x][y].setAlive(false);
            Cell.getCells()[x][y].setNextGenAlive(false);
            Main.getMain().paintWithMouse(Main.getMain().getGraphics(), x, y, Cell.getCells()[x][y], false, false);
        }

        Main.setLastCell(Cell.getCells()[x][y]);
        paintedWithMouse = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        // if a pattern is selected the user cant draw by dragging the mouse
        if (PatternPanel.isPattern() && SwingUtilities.isLeftMouseButton(e)) {
            return;
        }

        xDifference = (Cell.getCellWidth() * Main.getZoomFactor());
        yDifference = (Cell.getCellHeight() * Main.getZoomFactor());

        // converts x and y to correct value, even when zoomed in
        int x = (int) (e.getX() / xDifference - (Main.getxOffset() / xDifference));
        int y = (int) (e.getY() / yDifference - (Main.getyOffset() / yDifference));

        if (x < 0 || x >= Cell.getxGrids() || y < 0 || y >= Cell.getyGrids()) {
            Main.setLastCell(null);
            return;
        }

        if (SwingUtilities.isLeftMouseButton(e)) {
            Cell.getCells()[x][y].setAlive(true);
            Cell.getCells()[x][y].setNextGenAlive(true);
            Main.getMain().paintWithMouse(Main.getMain().getGraphics(), x, y, Cell.getCells()[x][y], true, true);
            paintedWithMouse = true;
        } else if (SwingUtilities.isRightMouseButton(e)) {
            Cell.getCells()[x][y].setAlive(false);
            Cell.getCells()[x][y].setNextGenAlive(false);
            Main.getMain().paintWithMouse(Main.getMain().getGraphics(), x, y, Cell.getCells()[x][y], false, true);
            paintedWithMouse = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paintedWithMouse && !PatternPanel.isPattern()) {
            // Main.getMain().repaint();
            paintedWithMouse = false;
            Main.setLastCell(null);
        }
    }

    // reset the zoom if mouse wheel has been clicked
    @Override
    public void mouseClicked(MouseEvent e) {

        super.mouseClicked(e);

        if (SwingUtilities.isMiddleMouseButton(e)) {
            hasZoomed = true;
            Main.resetZoom();
            Main.getMain().zoom(Main.getMain().getGraphics());
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);

        hasZoomed = true;

        // Zoom in
        if (e.getWheelRotation() < 0) {
            Main.setZoomFactor(Main.getZoomFactor() * 1.1);
            Main.getMain().zoom(Main.getMain().getGraphics());
        }
        // Zoom out
        if (e.getWheelRotation() > 0) {
            Main.setZoomFactor(Main.getZoomFactor() / 1.1);
            Main.getMain().zoom(Main.getMain().getGraphics());
        }
    }

    public static boolean isHasZoomed() {
        return hasZoomed;
    }

    public static void setHasZoomed(boolean hasZoomed) {
        MouseCellListener.hasZoomed = hasZoomed;
    }
}