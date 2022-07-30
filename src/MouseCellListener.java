package src;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.SwingUtilities;

public class MouseCellListener extends MouseAdapter {

    private double xDifference;
    private double yDifference;

    private static boolean paintedWithMouse;

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        xDifference = (Cell.getCellWidth() * GoLMain.getZoomFactor());
        yDifference = (Cell.getCellHeight() * GoLMain.getZoomFactor());

        // // converts x and y to correct value, even when zoomed in
        int x = (int) (e.getX() / xDifference - (GoLMain.getxOffset() / xDifference));
        int y = (int) (e.getY() / yDifference - (GoLMain.getyOffset() / yDifference));

        if (x < 0 || x >= Cell.getxGrids() || y < 0 || y >= Cell.getyGrids()) {
            return;
        }

        if (PatternPanel.isPattern() && SwingUtilities.isLeftMouseButton(e)) {

            CellPattern.createPattern(x, y);
            GoLMain.getMain().repaint();

        } else {
            
            if (x < 0 || x > Cell.getxGrids()|| y < 0 || y > Cell.getyGrids()) {
                return;
            }
    
            if (SwingUtilities.isLeftMouseButton(e)) {
                Cell.getCells()[x][y].setNextGenAlive(true);
            } else if (SwingUtilities.isRightMouseButton(e)) {
                Cell.getCells()[x][y].setNextGenAlive(false);
            } 
        }
        GoLMain.setLastCell(Cell.getCells()[x][y]);
        paintedWithMouse = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        // if a pattern is selected the user cant draw by dragging the mouse
        if (PatternPanel.isPattern() && SwingUtilities.isLeftMouseButton(e)) {
            return;
        }

        xDifference = (Cell.getCellWidth() * GoLMain.getZoomFactor());
        yDifference = (Cell.getCellHeight() * GoLMain.getZoomFactor());

        // converts x and y to correct value, even when zoomed in
        int x = (int) (e.getX() / xDifference - (GoLMain.getxOffset() / xDifference));
        int y = (int) (e.getY() / yDifference - (GoLMain.getyOffset() / yDifference));

        if (x < 0 || x >= Cell.getxGrids() || y < 0 || y >= Cell.getyGrids()) {
            GoLMain.setLastCell(null);
            return;
        }

        if (SwingUtilities.isLeftMouseButton(e)) {
            Cell.getCells()[x][y].setNextGenAlive(true);
            GoLMain.getMain().paintWithMouse(GoLMain.getMain().getGraphics(), x, y, Cell.getCells()[x][y], true);
            paintedWithMouse = true;
        } else if (SwingUtilities.isRightMouseButton(e)) {
            Cell.getCells()[x][y].setNextGenAlive(false);
            GoLMain.getMain().paintWithMouse(GoLMain.getMain().getGraphics(), x, y, Cell.getCells()[x][y], false);
            paintedWithMouse = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paintedWithMouse) {
            GoLMain.getMain().repaint();
            paintedWithMouse = false;
            GoLMain.setLastCell(null);
        }
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
