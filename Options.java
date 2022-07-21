import javax.swing.JButton;

public class Options {
    private static JButton resetButton, pauseButton, stepButton;

    private static boolean paused = false;
    private static boolean step = false;

    public Options() {
        resetButton = new JButton("Reset");
        resetButton.setFocusable(false);
        
        pauseButton = new JButton("Pause");
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(e -> togglePause());
        
        stepButton = new JButton("Step");
        stepButton.setFocusable(false);
        stepButton.setEnabled(false);
        stepButton.addActionListener(e -> makeAStep());

        Main.getTopPanel().add(resetButton);
        Main.getTopPanel().add(pauseButton);
        Main.getTopPanel().add(stepButton);

    }

    public static void togglePause() {
        paused = !paused;

        if (paused) {
            pauseButton.setText("Resume");
            stepButton.setEnabled(true);
        } else {
            pauseButton.setText("Pause");
            stepButton.setEnabled(false);
        }
    }

    public static void makeAStep() {
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
}
