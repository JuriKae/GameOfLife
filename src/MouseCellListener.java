package src;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.SwingUtilities;

public class MouseCellListener extends MouseAdapter {

    private static boolean hasZoomed;
    private static boolean paintedWithMouse;

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        boolean isLeftClick = SwingUtilities.isLeftMouseButton(e);

        // return if it is no left-or-right click
        if (!isLeftClick && !SwingUtilities.isRightMouseButton(e)) {
            return;
        }

        double xDifference = (Cell.getCellWidth() * Main.getZoomFactor());
        double yDifference = (Cell.getCellHeight() * Main.getZoomFactor());

        // converts x and y to correct value, even when zoomed in
        int x = (int) (e.getX() / xDifference - (Main.getxOffset() / xDifference));
        int y = (int) (e.getY() / yDifference - (Main.getyOffset() / yDifference));

        if (x < 0 || x >= Cell.getxGrids() || y < 0 || y >= Cell.getyGrids()) {
            return;
        }

        // if a Pattern is selected, return after drawing it
        if (PatternPanel.isPattern() && isLeftClick) {
            CellPattern.createPattern(x, y);
            Main.getMain().repaint();
            return;
        }

        Cell cell = Cell.getCells()[x][y];

        cell.setAlive(isLeftClick);

        Main.setLastCell(cell);
        paintedWithMouse = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        boolean isLeftClick = SwingUtilities.isLeftMouseButton(e);

        // return if Pattern is selected or if it is no left-or-right click
        if ((PatternPanel.isPattern() && isLeftClick) || (!isLeftClick && !SwingUtilities.isRightMouseButton(e))) {
            return;
        }

        double xDifference = (Cell.getCellWidth() * Main.getZoomFactor());
        double yDifference = (Cell.getCellHeight() * Main.getZoomFactor());

        // converts x and y to correct value, even when zoomed in
        int x = (int) (e.getX() / xDifference - (Main.getxOffset() / xDifference));
        int y = (int) (e.getY() / yDifference - (Main.getyOffset() / yDifference));

        if (x < 0 || x >= Cell.getxGrids() || y < 0 || y >= Cell.getyGrids()) {
            Main.setLastCell(null);
            return;
        }

        Cell cell = Cell.getCells()[x][y];

        cell.setAlive(isLeftClick);
        Main.getMain().paintWithMouse(Main.getMain().getGraphics(), x, y, cell, isLeftClick, true);
        paintedWithMouse = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paintedWithMouse && !PatternPanel.isPattern()) {
            Main.getMain().repaint();
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
            Main.getMain().repaint();
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);

        hasZoomed = true;

        // zoom in
        if (e.getWheelRotation() < 0) {
            Main.setZoomFactor(Main.getZoomFactor() * 1.1);
            Main.getMain().repaint();
        }
        // zoom out
        if (e.getWheelRotation() > 0) {
            Main.setZoomFactor(Main.getZoomFactor() / 1.1);
            Main.getMain().repaint();
        }
    }

    public static boolean isHasZoomed() {
        return hasZoomed;
    }

    public static void setHasZoomed(boolean hasZoomed) {
        MouseCellListener.hasZoomed = hasZoomed;
    }
}