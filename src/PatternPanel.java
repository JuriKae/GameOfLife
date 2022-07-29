package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PatternPanel {

    private static final int patternPanelWidth = 150;

    private static boolean isPattern;

    private static Pattern chosenPattern;

    private static JButton gliderButton, pulsarButton, lwssButton, hwssButton, pentaDecaButton;
    
    private static int buttonHeight = 150;
    private static int numberOfPatterns = 5;

    private static JButton[] buttonArray = new JButton[numberOfPatterns];

    public PatternPanel() {

        JPanel patternPanel = new JPanel();
        patternPanel.setBackground(Color.DARK_GRAY);
        patternPanel.setPreferredSize(new Dimension(patternPanelWidth, buttonHeight * numberOfPatterns));
        patternPanel.setLayout(new GridLayout(numberOfPatterns, 1, 20, 0));

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

        pentaDecaButton = new JButton("Pentadecathlon");
        pentaDecaButton.addActionListener(e -> {
            if (chosenPattern != Pattern.PENTDADECA) {
                chosenPattern = Pattern.PENTDADECA;
                isPattern = true;
                deselectButtons();
                pentaDecaButton.setBackground(Color.DARK_GRAY);
            } else {
                chosenPattern = null;
                isPattern = false;
                pentaDecaButton.setBackground(Color.GRAY);
            }

        });

        buttonArray[0] = gliderButton;
        buttonArray[1] = pulsarButton;
        buttonArray[2] = lwssButton;
        buttonArray[3] = hwssButton;
        buttonArray[4] = pentaDecaButton;

        for (JButton button : buttonArray) {
            button.setFocusable(false);
            button.setBackground(Color.GRAY);
            patternPanel.add(button);
        }

        // creates a scroll pane and puts the patternPanel inside
        // only show the vertical scollbar and increase the scrolling speed
        JScrollPane scrollPane = new JScrollPane(patternPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        GoLMain.getFrame().add(scrollPane, BorderLayout.EAST);
    }

    public static void deselectButtons() {
        for (JButton button : buttonArray) {
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
