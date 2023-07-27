package src;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class LayoutMaker {

    public LayoutMaker(JButton[] buttons, JSlider delaySlider) {

        JPanel topPanel = GamePanel.getTopPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        topPanel.setLayout(new GridBagLayout());

        gbc.insets = new Insets(3, -30, 3, 100);
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(buttons[0], gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(buttons[1], gbc);

        gbc.insets = new Insets(3, -70, 3, 0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        topPanel.add(buttons[2], gbc);

        gbc.insets = new Insets(3, -70, 3, 0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        buttons[3].setPreferredSize(new Dimension(50, 30));
        topPanel.add(buttons[3], gbc);

        gbc.insets = new Insets(3, -10, 3, 10);
        gbc.gridx = 2;
        gbc.gridy = 1;
        buttons[4].setPreferredSize(new Dimension(50, 30));
        topPanel.add(buttons[4], gbc);

        gbc.insets = new Insets(3, 30, 3, 0);
        gbc.gridx = 3;
        gbc.gridy = 0;
        topPanel.add(BasicOptions.getGenerationLabel(), gbc);

        gbc.insets = new Insets(3, 0, 3, 0);
        gbc.gridx = 3;
        gbc.gridy = 1;
        topPanel.add(buttons[5], gbc);

        gbc.insets = new Insets(0, 40, 0, 0);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        topPanel.add(delaySlider, gbc);
    }
}
