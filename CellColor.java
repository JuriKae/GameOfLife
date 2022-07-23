import java.awt.Color;

public class CellColor {

    private static Color[] rainbowColors = { new Color(148, 0, 211), new Color(75, 0, 130), new Color(0, 0, 255),
            new Color(0, 255, 0), new Color(255, 255, 0), new Color(255, 127, 0), new Color(255, 0, 0) };
    private static int rainbowColorIndex = 0;

    private static Color randomColor = null;
    private static int generationCounter = 1;

    public static synchronized void handleCellColor(Cell cell) {
        switch (BasicOptions.getColorMode()) {
            case Normal:
                cell.setAliveColor(Color.WHITE);
                cell.setDeadColor(Color.BLACK);
                break;
            case Rainbow:
                cell.setAliveColor(rainbowColors[rainbowColorIndex]);
                cell.setDeadColor(Color.BLACK);
                rainbowColorIndex++;
                if (rainbowColorIndex > 6) {
                    rainbowColorIndex = 0;
                }
                break;
            case Random:
                cell.setAliveColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
                        (int) (Math.random() * 255)));
                cell.setDeadColor(Color.BLACK);
                break;
            case Specific:
                // cell.setAliveColor(chosenColor);
                cell.setDeadColor(Color.BLACK);
                break;
        }
    }

    public static synchronized void handleGenerationColor(Cell cell) {
        switch (BasicOptions.getColorMode()) {
            case Normal:
                cell.setAliveColor(Color.WHITE);
                cell.setDeadColor(Color.BLACK);
                break;
            case Rainbow:
                cell.setAliveColor(rainbowColors[rainbowColorIndex]);
                cell.setDeadColor(Color.BLACK);
                rainbowColorIndex = Main.getGeneration() % 7;
                break;
            case Random:
                //TO-DO: Fix that color doesnt change at first step

                //ensures that only one random color is created per generation
                // probably a really bad solution for that problem
                if (generationCounter == Main.getGeneration()) {
                    randomColor = new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
                            (int) (Math.random() * 255));
                    generationCounter++;
                }

                cell.setAliveColor(randomColor);
                cell.setDeadColor(Color.BLACK);
                break;
            case Specific:
                // cell.setAliveColor(chosenColor);
                cell.setDeadColor(Color.BLACK);
                break;
        }
    }

    public static void setGenerationCounter(int generationCounter) {
        CellColor.generationCounter = generationCounter;
    }
}
