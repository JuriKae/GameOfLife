import java.awt.Color;

public class CellColor {

    private static Color[] rainbowColors = { new Color(148, 0, 211), new Color(75, 0, 130), new Color(0, 0, 255),
            new Color(0, 255, 0), new Color(255, 255, 0), new Color(255, 127, 0), new Color(255, 0, 0) };
    private static int rainbowColorIndex = 0;

    public static synchronized void handleCellColor(Cell cell) {
        switch (BasicOptions.getColorMode()) {
            case NORMAL:
                cell.setAliveColor(Color.WHITE);
                cell.setDeadColor(Color.BLACK);
                break;
            case RAINBOW:
                cell.setAliveColor(rainbowColors[rainbowColorIndex]);
                cell.setDeadColor(Color.BLACK);
                rainbowColorIndex++;
                if (rainbowColorIndex > 6) {
                    rainbowColorIndex = 0;
                }
                break;
            case RANDOM:
                cell.setAliveColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
                cell.setDeadColor(Color.BLACK);
                break;
            case SPECIFIC:
                // cell.setAliveColor(chosenColor);
                cell.setDeadColor(Color.BLACK);
                break;
        }
    }
}
