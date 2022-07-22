import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
            Cell.getCells()[x][y].setNextGenAlive(true);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            Cell.getCells()[x][y].setNextGenAlive(false);
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
            Cell.getCells()[x][y].setNextGenAlive(true);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            Cell.getCells()[x][y].setNextGenAlive(false);
        }
        Main.getMain().repaint();
    }
}
