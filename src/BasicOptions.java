package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class BasicOptions {
    private static JButton startButton, resetButton, pauseButton, previousButton, stepButton, optionsButton;
    private static JLabel generationLabel;

    private static JSlider delaySlider;

    private static boolean paused = true;
    private static boolean step = false;

    private static int delay = 50;

    private static Font buttonFont = new Font(null, Font.BOLD, 12);
    private static Color buttonColor = new Color(0, 71, 179);

    public BasicOptions(GamePanel panel) {

        startButton = new JButton("Start");
        startButton.addActionListener(e -> startGame());

        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> reset());

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> togglePause());
        pauseButton.setEnabled(false);

        previousButton = new JButton("Back");
        previousButton.addActionListener(e -> {
            // code executes when user makes one step back
            previousButton.setEnabled(false);
            Cell.takeAStepBack();
            int generation = GamePanel.oneGenerationBack();
            generationLabel.setText("Generation: " + generation);
        });
        previousButton.setEnabled(false);
        
        stepButton = new JButton("Step");
        stepButton.addActionListener(e -> {
            // code executes when user makes one step
            previousButton.setEnabled(true);
            pauseButton.setEnabled(true);
            pauseButton.setText("Resume");
            startButton.setEnabled(false);
            step = true;
        });

        optionsButton = new JButton("Options");
        optionsButton.addActionListener(e -> {

            JFrame optionsFrame = AdvancedOptions.getOptionsFrame();

            // check if optionsFrame fits to the right of the main frame
            // if not, set its location relative to the main frame
            Point point = new Point(GamePanel.getFrame().getX() + GamePanel.getFrame().getWidth(), GamePanel.getFrame().getY());
            Point rightMostPoint = new Point((int) (point.getX() + optionsFrame.getWidth()), (int) point.getY());

            if (SwingUtil.isLocationInScreenBounds(rightMostPoint)) {
                optionsFrame.setLocation(point);
            } else {
                optionsFrame.setLocationRelativeTo(panel);
            }
            optionsFrame.setVisible(true);
        });

        generationLabel = new JLabel("Generation: 1");
        generationLabel.setFont(new Font(null, Font.BOLD, 16));
        generationLabel.setPreferredSize(new Dimension(140, 20));
        generationLabel.setForeground(Color.WHITE);

        JButton[] buttonArray = { startButton, resetButton, pauseButton, previousButton, stepButton, optionsButton };
        for (JButton button : buttonArray) {
            button.setPreferredSize(new Dimension(100, 30));
            button.setFocusable(false);
            button.setBackground(buttonColor);
            button.setForeground(Color.WHITE);
            button.setFont(buttonFont);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        }

        delaySlider = new JSlider(JSlider.VERTICAL, 1, 100, 40);
        delaySlider.setPreferredSize(new Dimension(20, 70));
        delaySlider.setBackground(Color.BLACK);
        delaySlider.setToolTipText("Delay between generations");
        delaySlider.addChangeListener(e -> {

            // function to calculate logarithmic values for better slider experience
            delay = (int) Math.exp((Math.log(1000) - Math.log(1)) / (100 - 1) * (delaySlider.getValue() - 1));

        });

        new LayoutMaker(buttonArray, delaySlider);
    }

    public static void startGame() {
        if (paused) {
            togglePause();
            startButton.setEnabled(false);
        }
    }

    public static void reset() {
        GamePanel.setReset(true);

        if (!paused) {
            togglePause();
        }

        startButton.setEnabled(true);
        previousButton.setEnabled(false);
        pauseButton.setEnabled(false);
        pauseButton.setText("Pause");
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

    public static int getDelay() {
        return delay;
    }

    public static Color getButtonColor() {
        return buttonColor;
    }
}
