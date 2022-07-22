import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

public class Options {
    private static JButton startButton, resetButton, pauseButton, stepButton;
    private static JLabel generationLabel;

    private static boolean paused = true;
    private static boolean step = false;

    public Options() {
        startButton = new JButton("Start");
        startButton.setFocusable(false);
        startButton.addActionListener(e -> startGame());

        resetButton = new JButton("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> reset());
        
        pauseButton = new JButton("Pause");
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(e -> togglePause());
        pauseButton.setEnabled(false);
        
        stepButton = new JButton("Step");
        stepButton.setFocusable(false);
        stepButton.addActionListener(e -> makeAStep());
        

        generationLabel = new JLabel("Generation: 1");
        generationLabel.setFont(new Font(null, Font.BOLD, 20));
        generationLabel.setForeground(Color.WHITE);


        Main.getTopPanel().add(startButton);
        Main.getTopPanel().add(resetButton);
        Main.getTopPanel().add(pauseButton);
        Main.getTopPanel().add(stepButton);
        Main.getTopPanel().add(generationLabel);

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
        step = true;
    }

    public static boolean isPaused() {
        return paused;
    }

    public static boolean isStep() {
        return step;
    }

    public static void setStep(boolean step) {
        Options.step = step;
    }

    public static JLabel getGenerationLabel() {
        return generationLabel;
    }
}
