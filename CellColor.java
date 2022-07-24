import java.awt.Color;

public class CellColor {

    private static Color[] rainbowColors = { new Color(148, 0, 211), new Color(75, 0, 130), new Color(0, 0, 255),
            new Color(0, 255, 0), new Color(255, 255, 0), new Color(255, 127, 0), new Color(255, 0, 0) };
    private static int rainbowColorIndex = 0;

    private static Color rainbowColor = null;
    private static Color randomColor = null;

    public static synchronized void handleCellColor(Cell cell) {
        switch (AdvancedOptions.getColorMode()) {
            case Normal:
                cell.setAliveColor(Color.WHITE);
                break;
            case Rainbow:
                cell.setAliveColor(rainbowColors[rainbowColorIndex]);
                rainbowColorIndex++;
                if (rainbowColorIndex > 6) {
                    rainbowColorIndex = 0;
                }
                break;
            case Random:
                cell.setAliveColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
                        (int) (Math.random() * 255)));
                break;
            case Specific:
                cell.setAliveColor(AdvancedOptions.getChosenColor());
                break;
        }
    }

    public static synchronized void handleGenerationColor(Cell cell) {
        switch (AdvancedOptions.getColorMode()) {
            case Normal:
                cell.setAliveColor(Color.WHITE);
                break;
            case Rainbow:
                cell.setAliveColor(rainbowColor);
                break;
            case Random:
                cell.setAliveColor(randomColor);
                break;
            case Specific:
                cell.setAliveColor(AdvancedOptions.getChosenColor());
                break;
        }
    }

    // generate colors for the one-color generation mode
    public static void generateColors() {
        rainbowColorIndex = Main.getGeneration() % 7;
        rainbowColor = rainbowColors[rainbowColorIndex];

        randomColor = new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
        (int) (Math.random() * 255));
        System.out.println("generate");
    }

    public static Color getRandomColor() {
        return randomColor;
    }

    public static void setRandomColor(Color randomColor) {
        CellColor.randomColor = randomColor;
    }
}
