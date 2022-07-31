package src;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class LayoutMaker {

    public LayoutMaker() {

        JPanel topPanel = Main.getTopPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        topPanel.setLayout(new GridBagLayout());

        gbc.insets = new Insets(3, -30, 3, 100);
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(BasicOptions.getStartButton(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(BasicOptions.getResetButton(), gbc);

        gbc.insets = new Insets(3, -70, 3, 0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        topPanel.add(BasicOptions.getPauseButton(), gbc);

        gbc.insets = new Insets(3, -70, 3, 0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        BasicOptions.getPreviousButton().setPreferredSize(new Dimension(50, 30));
        topPanel.add(BasicOptions.getPreviousButton(), gbc);

        gbc.insets = new Insets(3, -10, 3, 10);
        gbc.gridx = 2;
        gbc.gridy = 1;
        BasicOptions.getStepButton().setPreferredSize(new Dimension(50, 30));
        topPanel.add(BasicOptions.getStepButton(), gbc);

        gbc.insets = new Insets(3, 30, 3, 0);
        gbc.gridx = 3;
        gbc.gridy = 0;
        topPanel.add(BasicOptions.getGenerationLabel(), gbc);

        gbc.insets = new Insets(3, 0, 3, 0);
        gbc.gridx = 3;
        gbc.gridy = 1;
        topPanel.add(BasicOptions.getOptionsButton(), gbc);

        gbc.insets = new Insets(0, 40, 0, 0);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        topPanel.add(BasicOptions.getDelaySlider(), gbc);
    }
}
