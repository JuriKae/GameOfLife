import javax.swing.JButton;

public class Options {
    private static JButton startButton, resetButton, pauseButton, stepButton;

    private static boolean paused = false;
    private static boolean step = false;

    public Options() {
        startButton = new JButton("Start");
        startButton.setFocusable(false);
        startButton.addActionListener(e -> start());

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


        Main.getTopPanel().add(startButton);
        Main.getTopPanel().add(resetButton);
        Main.getTopPanel().add(pauseButton);
        Main.getTopPanel().add(stepButton);

    }

    public static void start() {
        if (!Main.getThread().isAlive()) {
            Main.getThread().start();
            startButton.setEnabled(false);
            pauseButton.setEnabled(true);
        } else {
            startButton.setEnabled(false);
            togglePause();
        }
    }

    public static void reset() {
        Cell[][] cells = Cell.getCells();

        for (int i = 0; i < Main.getxGrids(); i++) {
            for (int j = 0; j < Main.getyGrids(); j++) {
                cells[i][j].setAlive(false);
                cells[i][j].setNextGenAlive(false);
            }
        }

        if (!paused) {
            togglePause();
        }

        pauseButton.setEnabled(false);
        pauseButton.setText("Pause");

        Main.setInitialized(false);
        Main.getMain().repaint();

        startButton.setEnabled(true);
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
            start();
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
}
