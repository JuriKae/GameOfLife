import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class BasicOptions {
    private static JButton startButton, resetButton, pauseButton, previousButton, stepButton, optionsButton;
    private static JLabel generationLabel;

    private static JComboBox<Object> cellModeBox, cellSizeBox;
    private static JSlider delaySlider;

    private static boolean paused = true;
    private static boolean step = false;

    private static boolean optionsShown = false;

    private static ColorMode colorMode = ColorMode.RANDOM;

    private static AliveCellMode aliveCellMode = AliveCellMode.RANDOM;

    private static AliveCellMode[] cellModes = { AliveCellMode.RANDOM, AliveCellMode.LINE, AliveCellMode.EMPTY };
    private static Integer[] cellSizes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };

    private static float percOfAliveCells = 40;

    private static int delay = 50;

    private static Font buttonFont = new Font(null, Font.BOLD, 12);

    public BasicOptions() {
        startButton = new JButton("Start");
        startButton.addActionListener(e -> startGame());

        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> reset());

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> togglePause());
        pauseButton.setEnabled(false);

        previousButton = new JButton("Back");
        previousButton.addActionListener(e -> makeAStepBack());
        previousButton.setEnabled(false);

        stepButton = new JButton("Step");
        stepButton.addActionListener(e -> makeAStep());

        optionsButton = new JButton("Options");
        optionsButton.addActionListener(e -> toggleShowOptions());

        generationLabel = new JLabel("Generation: 1");
        generationLabel.setFont(new Font(null, Font.BOLD, 16));
        generationLabel.setPreferredSize(new Dimension(130, 20));
        generationLabel.setForeground(Color.WHITE);

        JButton[] buttonArray = { startButton, resetButton, pauseButton, previousButton, stepButton, optionsButton };
        for (JButton button : buttonArray) {
            button.setPreferredSize(new Dimension(100, 30));
            button.setFocusable(false);
            button.setBackground(new Color(0, 71, 179));
            button.setForeground(Color.WHITE);
            button.setFont(buttonFont);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        }

        cellModeBox = new JComboBox<>(cellModes);
        cellModeBox.setVisible(false);
        cellModeBox.setPreferredSize(new Dimension(100, 30));
        cellModeBox.addActionListener(e -> {
            aliveCellMode = (AliveCellMode) cellModeBox.getSelectedItem();
            reset();
        });

        cellSizeBox = new JComboBox<>(cellSizes);
        cellSizeBox.setVisible(false);
        cellSizeBox.setEditable(true);
        cellSizeBox.setPreferredSize(new Dimension(100, 30));
        cellSizeBox.setSelectedIndex(5);
        cellSizeBox.addActionListener(e -> {
            Integer cellSizeString = (Integer) cellSizeBox.getSelectedItem();
            Cell.setCellWidth(cellSizeString);
            Cell.setCellHeight(cellSizeString);
            reset();
        });

        delaySlider = new JSlider(JSlider.VERTICAL, 1, 100, 40);
        delaySlider.setPreferredSize(new Dimension(20, 70));
        delaySlider.setBackground(Color.BLACK);
        delaySlider.addChangeListener(e -> {

            // function to calculate logarithmic values for better slider experience
            delay = (int) Math.exp((Math.log(1000) - Math.log(1)) / (100 - 1) * (delaySlider.getValue() - 1));

        });

        new LayoutMaker();
    }

    public static void startGame() {
        if (paused) {
            paused = false;
            startButton.setEnabled(false);
            pauseButton.setEnabled(true);
        }
    }

    public static void reset() {
        Main.setReset(true);
        Cell.initializeCells();

        if (!paused) {
            togglePause();
        }

        pauseButton.setEnabled(false);
        pauseButton.setText("Pause");

        Main.setInitialized(false);
        Main.getMain().repaint();

        startButton.setEnabled(true);

        Main.createThread();
    }

    public static void togglePause() {
        paused = !paused;

        if (paused) {
            pauseButton.setText("Resume");
            stepButton.setEnabled(true);
            previousButton.setEnabled(true);
            pauseButton.setEnabled(true);
        } else {
            pauseButton.setText("Pause");
            stepButton.setEnabled(false);
            previousButton.setEnabled(false);
            pauseButton.setEnabled(true);
        }
    }

    public static void makeAStep() {
        if (!Main.getThread().isAlive()) {
            startGame();
            togglePause();
        }
        previousButton.setEnabled(true);
        pauseButton.setEnabled(true);
        pauseButton.setText("Resume");
        startButton.setEnabled(false);
        step = true;
    }

    public static void makeAStepBack() {
        previousButton.setEnabled(false);

        for (int i = 0; i < Cell.getxGrids() - 1; i++) {
            for (int j = 0; j < Cell.getyGrids() - 1; j++) {
                if (Cell.getCells()[i][j].isLastGenAlive()) {
                    Cell.getCells()[i][j].setNextGenAlive(true);
                } else {
                    Cell.getCells()[i][j].setNextGenAlive(false);
                }
            }
        }
        Main.setGeneration(Main.getGeneration() - 1);
        generationLabel.setText("Generation: " + Main.getGeneration());
        Main.getMain().repaint();
    }

    public static void toggleShowOptions() {
        cellModeBox.setVisible(!optionsShown);
        cellSizeBox.setVisible(!optionsShown);

        startButton.setVisible(optionsShown);
        resetButton.setVisible(optionsShown);
        pauseButton.setVisible(optionsShown);
        stepButton.setVisible(optionsShown);
        previousButton.setVisible(optionsShown);
        generationLabel.setVisible(optionsShown);
        delaySlider.setVisible(optionsShown);

        if (optionsShown)
            optionsButton.setText("Options");
        else
            optionsButton.setText("Exit");

        optionsShown = !optionsShown;
    }

    public static boolean isPaused() {
        return paused;
    }

    public static boolean isStep() {
        return step;
    }

    public static void setStep(boolean step) {
        BasicOptions.step = step;
    }

    public static JLabel getGenerationLabel() {
        return generationLabel;
    }

    public static AliveCellMode getAliveCellMode() {
        return aliveCellMode;
    }

    public static float getPercOfAliveCells() {
        return percOfAliveCells;
    }

    public static JButton getStartButton() {
        return startButton;
    }

    public static JButton getResetButton() {
        return resetButton;
    }

    public static JButton getPauseButton() {
        return pauseButton;
    }

    public static JButton getStepButton() {
        return stepButton;
    }

    public static JButton getOptionsButton() {
        return optionsButton;
    }

    public static JComboBox<Object> getCellModeBox() {
        return cellModeBox;
    }

    public static JComboBox<Object> getDelayBox() {
        return cellSizeBox;
    }

    public static int getDelay() {
        return delay;
    }

    public static JButton getPreviousButton() {
        return previousButton;
    }

    public static JSlider getDelaySlider() {
        return delaySlider;
    }

    public static ColorMode getColorMode() {
        return colorMode;
    }

    public static void setColorMode(ColorMode colorMode) {
        BasicOptions.colorMode = colorMode;
    }
}
