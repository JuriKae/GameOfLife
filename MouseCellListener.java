import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.SwingUtilities;

public class MouseCellListener extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        int x = e.getX() / Cell.getCellWidth();
        int y = e.getY() / Cell.getCellHeight();

        if (x < 0 || x > Cell.getxGrids() - 1 || y < 0 || y > Cell.getyGrids() - 1) {
            return;
        }

        if (SwingUtilities.isLeftMouseButton(e)) {
            Cell.getCells()[(int) x][(int) y].setNextGenAlive(true);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            Cell.getCells()[(int) x][(int) y].setNextGenAlive(false);
        } 
        Main.getMain().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        int x = e.getX() / Cell.getCellWidth();
        int y = e.getY() / Cell.getCellHeight();

        if (x < 0 || x > Cell.getxGrids() - 1 || y < 0 || y > Cell.getyGrids() - 1) {
            return;
        }
        if (SwingUtilities.isLeftMouseButton(e)) {
            Cell.getCells()[(int) x][(int) y].setNextGenAlive(true);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            Cell.getCells()[(int) x][(int) y].setNextGenAlive(false);
        }
        Main.getMain().repaint();
    }

    // reset the zoom if mouse wheel has been clicked
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        if (SwingUtilities.isMiddleMouseButton(e)) {
            Main.resetZoom();
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);

        Main.setHasZoomed(true);

        // Zoom in
        if (e.getWheelRotation() < 0) {
            Main.setZoomFactor(Main.getZoomFactor() * 1.1);
            Main.getMain().repaint();
        }
        // Zoom out
        if (e.getWheelRotation() > 0) {
            Main.setZoomFactor(Main.getZoomFactor() / 1.1);
            Main.getMain().repaint();
        }
    }
}
