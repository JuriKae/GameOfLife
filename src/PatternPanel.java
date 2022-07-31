package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PatternPanel {

    private static final int patternPanelWidth = 150;

    private static boolean isPattern;

    private static Pattern chosenPattern;

    private static int buttonHeight = 150;
    private static int numberOfPatterns = 6;

    private static JButton[] buttonArray = new JButton[numberOfPatterns];

    public PatternPanel() {

        JPanel patternPanel = new JPanel();
        patternPanel.setBackground(Color.DARK_GRAY);
        patternPanel.setPreferredSize(new Dimension(patternPanelWidth, buttonHeight * numberOfPatterns));
        patternPanel.setLayout(new GridLayout(numberOfPatterns, 1, 20, 0));

        ImageIcon gliderIcon = new ImageIcon(PatternPanel.class.getResource("icons/gliderIcon.png"));
        JButton gliderButton = new JButton("Glider");
        gliderButton.setIcon(gliderIcon);
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

        ImageIcon smallShipIcon = new ImageIcon(PatternPanel.class.getResource("icons/smallShipIcon.png"));
        JButton lwssButton = new JButton("Small spaceship");
        lwssButton.setIcon(smallShipIcon);
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

        ImageIcon bigShipIcon = new ImageIcon(PatternPanel.class.getResource("icons/bigShipIcon.png"));
        JButton hwssButton = new JButton("Big spaceship");
        hwssButton.setIcon(bigShipIcon);
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

        ImageIcon pulsarIcon = new ImageIcon(PatternPanel.class.getResource("icons/pulsarIcon.png"));
        JButton pulsarButton = new JButton("Pulsar");
        pulsarButton.setIcon(pulsarIcon);
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

        ImageIcon pentaDecaIcon = new ImageIcon(PatternPanel.class.getResource("icons/pentaDecaIcon.png"));
        JButton pentaDecaButton = new JButton("Pentadecathlon");
        pentaDecaButton.setIcon(pentaDecaIcon);
        pentaDecaButton.addActionListener(e -> {
            if (chosenPattern != Pattern.Pentadeca) {
                chosenPattern = Pattern.Pentadeca;
                isPattern = true;
                deselectButtons();
                pentaDecaButton.setBackground(Color.DARK_GRAY);
            } else {
                chosenPattern = null;
                isPattern = false;
                pentaDecaButton.setBackground(Color.GRAY);
            }
        });

        ImageIcon playButtonIcon = new ImageIcon(PatternPanel.class.getResource("icons/playButtonIcon.png"));
        JButton playButtonButton = new JButton("Playbutton");
        playButtonButton.setIcon(playButtonIcon);
        playButtonButton.addActionListener(e -> {
            if (chosenPattern != Pattern.Playbutton) {
                chosenPattern = Pattern.Playbutton;
                isPattern = true;
                deselectButtons();
                playButtonButton.setBackground(Color.DARK_GRAY);
            } else {
                chosenPattern = null;
                isPattern = false;
                playButtonButton.setBackground(Color.GRAY);
            }
        });

        buttonArray[0] = gliderButton;
        buttonArray[1] = lwssButton;
        buttonArray[2] = hwssButton;
        buttonArray[3] = pulsarButton;
        buttonArray[4] = pentaDecaButton;
        buttonArray[5] = playButtonButton;

        for (JButton button : buttonArray) {
            button.setFocusable(false);
            button.setBackground(Color.GRAY);
            patternPanel.add(button);
            button.setVerticalTextPosition(AbstractButton.TOP);
            button.setHorizontalTextPosition(AbstractButton.CENTER);
            button.setFont(new Font(null, Font.BOLD, 14));
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE));

            // create a scaled icon that fits on the JButton
            if (button.getIcon() != null) {
                Image buttonImage = ((ImageIcon) button.getIcon()).getImage();
                Image newButtonImage = buttonImage.getScaledInstance(buttonHeight - 40, buttonHeight - 40,
                        Image.SCALE_SMOOTH);
                gliderIcon = new ImageIcon(newButtonImage);
                button.setIcon(gliderIcon);
            }
        }

        // creates a scroll pane and puts the patternPanel inside
        // only show the vertical scollbar and increase the scrolling speed
        JScrollPane scrollPane = new JScrollPane(patternPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        Main.getFrame().add(scrollPane, BorderLayout.EAST);
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
