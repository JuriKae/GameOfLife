package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PatternPanel extends JPanel {

    private static final int patternPanelWidth = 150;

    private static boolean isPattern;

    private static Pattern chosenPattern;

    private static JButton gliderButton, pulsarButton, lwssButton, hwssButton;

    private static JButton[] buttons = new JButton[4];

    public PatternPanel() {

        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(patternPanelWidth, 0));
        this.setLayout(new GridLayout(5, 1, 20, 0));

        gliderButton = new JButton("Glider");
        gliderButton.addActionListener(e -> {
            if (chosenPattern != Pattern.Glider) {
                chosenPattern = Pattern.Glider;
                isPattern = true;
                deselectButtons();
                gliderButton.setBackground(Color.DARK_GRAY);
            } else {
                chosenPattern = null;
                isPattern = false;
                gliderButton.setBackground(Color.GRAY);
            }
        });

        pulsarButton = new JButton("Pulsar");
        pulsarButton.addActionListener(e -> {
            if (chosenPattern != Pattern.Pulsar) {
                chosenPattern = Pattern.Pulsar;
                isPattern = true;
                deselectButtons();
                pulsarButton.setBackground(Color.DARK_GRAY);
            } else {
                chosenPattern = null;
                isPattern = false;
                pulsarButton.setBackground(Color.GRAY);
            }
            
        });

        lwssButton = new JButton("Lightweight spaceship");
        lwssButton.addActionListener(e -> {
            if (chosenPattern != Pattern.LWSS) {
                chosenPattern = Pattern.LWSS;
                isPattern = true;
                deselectButtons();
                lwssButton.setBackground(Color.DARK_GRAY);
            } else {
                chosenPattern = null;
                isPattern = false;
                lwssButton.setBackground(Color.GRAY);
            }
            
        });

        hwssButton = new JButton("Heavyweight spaceship");
        hwssButton.addActionListener(e -> {
            if (chosenPattern != Pattern.HWSS) {
                chosenPattern = Pattern.HWSS;
                isPattern = true;
                deselectButtons();
                hwssButton.setBackground(Color.DARK_GRAY);
            } else {
                chosenPattern = null;
                isPattern = false;
                hwssButton.setBackground(Color.GRAY);
            }
            
        });


        buttons[0] = gliderButton;
        buttons[1] = pulsarButton;
        buttons[2] = lwssButton;
        buttons[3] = hwssButton;

        for (JButton button : buttons) {
            button.setFocusable(false);
            button.setBackground(Color.GRAY);
        }

        this.add(gliderButton);
        this.add(pulsarButton);
        this.add(lwssButton);
        this.add(hwssButton);

        GoLMain.getFrame().add(this, BorderLayout.EAST);
    }

    public static void deselectButtons() {
        for (JButton button : buttons) {
            button.setBackground(Color.GRAY);
        }
    }

    public static int getPatternpanelwidth() {
        return patternPanelWidth;
    }

    public static boolean isPattern() {
        return isPattern;
    }

    public static void setPattern(boolean isPattern) {
        PatternPanel.isPattern = isPattern;
    }

    public static Pattern getChosenPattern() {
        return chosenPattern;
    }
}
