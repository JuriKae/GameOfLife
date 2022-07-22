import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class LayoutMaker {

    private static GridBagConstraints gbc;
    private static JPanel topPanel;

    public LayoutMaker() {
        topPanel = Main.getTopPanel();
        topPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

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
        topPanel.add(BasicOptions.getPauseButton(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        topPanel.add(BasicOptions.getStepButton(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        topPanel.add(BasicOptions.getPreviousButton(), gbc);

        gbc.insets = new Insets(3, 100, 3, 0);
        gbc.gridx = 2;
        gbc.gridy = 0;
        topPanel.add(BasicOptions.getGenerationLabel(), gbc);

        gbc.insets = new Insets(3, 80, 3, 0);
        gbc.gridx = 2;
        gbc.gridy = 1;
        topPanel.add(BasicOptions.getOptionsButton(), gbc);

        // gbc.insets = new Insets(3, 80, 3, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(BasicOptions.getCellModeBox(), gbc);

        // gbc.insets = new Insets(3, 80, 3, 0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        topPanel.add(BasicOptions.getDelayBox(), gbc);
    }
}
