import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class BasicOptions {
    private static JButton startButton, resetButton, pauseButton, stepButton, optionsButton;
    private static JLabel generationLabel;

    private static JComboBox<Object> cellModeBox, delayBox;

    private static boolean paused = true;
    private static boolean step = false;

    private static boolean optionsShown = false;

    private static AliveCellMode aliveCellMode = AliveCellMode.RANDOM;

    private static AliveCellMode[] cellModes = { AliveCellMode.RANDOM, AliveCellMode.LINE, AliveCellMode.EMPTY };

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

        stepButton = new JButton("Step");
        stepButton.addActionListener(e -> makeAStep());

        optionsButton = new JButton("Options");
        optionsButton.addActionListener(e -> toggleShowOptions());

        generationLabel = new JLabel("Generation: 1");
        generationLabel.setFont(new Font(null, Font.BOLD, 16));
        generationLabel.setPreferredSize(new Dimension(130, 20));
        generationLabel.setForeground(Color.WHITE);

        JButton[] buttonArray = { startButton, resetButton, pauseButton, stepButton, optionsButton };
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
        cellModeBox.addActionListener(e -> aliveCellMode = (AliveCellMode) cellModeBox.getSelectedItem());

        delayBox = new JComboBox<>();
        delayBox.setVisible(false);
        delayBox.setEditable(true);
        delayBox.addActionListener(e -> {
            String stringDelay = (String) delayBox.getSelectedItem();
            delay = Integer.parseInt(stringDelay);
            
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
            pauseButton.setEnabled(true);
        } else {
            pauseButton.setText("Pause");
            stepButton.setEnabled(false);
            pauseButton.setEnabled(true);
        }
    }

    public static void makeAStep() {
        if (!Main.getThread().isAlive()) {
            startGame();
            togglePause();
        }
        pauseButton.setEnabled(true);
        pauseButton.setText("Resume");
        startButton.setEnabled(false);
        step = true;
    }

    public static void toggleShowOptions() {
        cellModeBox.setVisible(!optionsShown);
        delayBox.setVisible(!optionsShown);

        startButton.setVisible(optionsShown);
        resetButton.setVisible(optionsShown);
        pauseButton.setVisible(optionsShown);
        stepButton.setVisible(optionsShown);
        generationLabel.setVisible(optionsShown);

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
        return delayBox;
    }

    public static int getDelay() {
        return delay;
    }
}
