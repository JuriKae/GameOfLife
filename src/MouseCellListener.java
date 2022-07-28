package src;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.SwingUtilities;

public class MouseCellListener extends MouseAdapter {

    private double xDifference;
    private double yDifference;

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        xDifference = (Cell.getCellWidth() * GoLMain.getZoomFactor());
        yDifference = (Cell.getCellHeight() * GoLMain.getZoomFactor());

        // converts x and y to correct value, even when zoomed in
        double x = e.getX() / xDifference - (GoLMain.getxOffset() / xDifference);
        double y = e.getY() / yDifference - (GoLMain.getyOffset() / yDifference);

        if (x < 0 || x > Cell.getxGrids()|| y < 0 || y > Cell.getyGrids()) {
            return;
        }

        if (SwingUtilities.isLeftMouseButton(e)) {
            Cell.getCells()[(int) x][(int) y].setNextGenAlive(true);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            Cell.getCells()[(int) x][(int) y].setNextGenAlive(false);
        } 
        GoLMain.getMain().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        xDifference = (Cell.getCellWidth() * GoLMain.getZoomFactor());
        yDifference = (Cell.getCellHeight() * GoLMain.getZoomFactor());

        // converts x and y to correct value, even when zoomed in
        double x = e.getX() / xDifference - (GoLMain.getxOffset() / xDifference);
        double y = e.getY() / yDifference - (GoLMain.getyOffset() / yDifference);

        if (x < 0 || (int) x >= Cell.getxGrids() || y < 0 || (int) y >= Cell.getyGrids()) {
            return;
        }
        if (SwingUtilities.isLeftMouseButton(e)) {
            Cell.getCells()[(int) x][(int) y].setNextGenAlive(true);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            Cell.getCells()[(int) x][(int) y].setNextGenAlive(false);
        }
        GoLMain.getMain().repaint();
    }

    // reset the zoom if mouse wheel has been clicked
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        if (SwingUtilities.isMiddleMouseButton(e)) {
            GoLMain.resetZoom();
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);

        GoLMain.setHasZoomed(true);

        // Zoom in
        if (e.getWheelRotation() < 0) {
            GoLMain.setZoomFactor(GoLMain.getZoomFactor() * 1.1);
            GoLMain.getMain().repaint();
        }
        // Zoom out
        if (e.getWheelRotation() > 0) {
            GoLMain.setZoomFactor(GoLMain.getZoomFactor() / 1.1);
            GoLMain.getMain().repaint();
        }
    }
}
