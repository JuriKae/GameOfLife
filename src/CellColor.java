package src;

import java.awt.Color;

public class CellColor {

    private static Color[] rainbowColors = { new Color(148, 0, 211), new Color(75, 0, 130), new Color(0, 0, 255),
            new Color(0, 255, 0), new Color(255, 255, 0), new Color(255, 127, 0), new Color(255, 0, 0) };
    private static int rainbowColorIndex = 0;

    private static Color rainbowColor = null;
    private static Color randomColor = null;

    private static long lastTimeCalled;

    // calls the correct function for changing the color of every cell
    public static void callChangeColorFunction() {

        ColorMode colorMode = AdvancedOptions.getColorMode();
        
        int xGrids = Cell.getxGrids();
        int yGrids = Cell.getyGrids();
        Cell[][] cells = Cell.getCells();

        // call the correct function, depending on the one-generation color option
        if (!AdvancedOptions.isOneGenerationColor()) {
            for (int i = 0; i < xGrids; i++) {
                for (int j = 0; j < yGrids; j++) {
                    changeCellColor(cells[i][j], colorMode);
                }
            }
        } else {
            for (int i = 0; i < xGrids; i++) {
                for (int j = 0; j < yGrids; j++) {
                    changeGenerationColor(cells[i][j], colorMode);
                }
            }
        }
    }

    // changes the color so that the cells of one generation can have different colors
    public static void changeCellColor(Cell cell, ColorMode colorMode) {
        switch (colorMode) {
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

    // changes the color so that every cell of one generation has the same color
    public static void changeGenerationColor(Cell cell, ColorMode colorMode) {
        switch (colorMode) {
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
    public static void generateColors(long timeWhenCalled) {
        // colors can only change every 0.2 seconds
        if (timeWhenCalled - lastTimeCalled < 200000000) {
            return;
        }
        
        if (++rainbowColorIndex > 6) {
            rainbowColorIndex = 0;
        }
        rainbowColor = rainbowColors[rainbowColorIndex];

        randomColor = new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
                (int) (Math.random() * 255));

        lastTimeCalled = System.nanoTime();
    }
}
