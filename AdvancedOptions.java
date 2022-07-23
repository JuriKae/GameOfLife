import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdvancedOptions {
    private static JFrame optionsFrame;
    private static JPanel colorPanel, firstCellsPanel, cellSizePanel;
    private static JLabel colorLabel, firstCellsLabel, cellSizeLabel;

    private static Font titleFont = new Font(null, Font.BOLD, 20);
    
    public AdvancedOptions() {
        optionsFrame = new JFrame();
        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionsFrame.setBackground(Color.BLACK);
        optionsFrame.setSize(500 + 16, 500 + 39);
        optionsFrame.setLayout(null);
        // optionsFrame.setResizable(false);

        
        fillColorPanel();
        fillFirstCellsPanel();
        fillCellSizePanel();


        optionsFrame.add(colorPanel);
        optionsFrame.add(firstCellsPanel);
        optionsFrame.add(cellSizePanel);

        optionsFrame.setLocationRelativeTo(null);
        optionsFrame.setVisible(false);
    }

    public static void fillColorPanel() {
        colorPanel = new JPanel();
        colorPanel.setLayout(null);
        colorPanel.setBounds(0, 0, 500, 200);
        colorPanel.setBackground(Color.BLACK);
        colorPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));


        colorLabel = new JLabel("Color Options");
        colorLabel.setBounds(10, 0, 200, 40);
        colorLabel.setForeground(Color.WHITE);
        colorLabel.setFont(titleFont);


        colorPanel.add(colorLabel);
    }

    public static void fillFirstCellsPanel() {
        firstCellsPanel = new JPanel();
        firstCellsPanel.setLayout(null);
        firstCellsPanel.setBounds(0, 200, 500, 150);
        firstCellsPanel.setBackground(Color.BLACK);
        firstCellsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));



        firstCellsLabel = new JLabel("Options for first alive cells");
        firstCellsLabel.setBounds(10, 0, 300, 40);
        firstCellsLabel.setForeground(Color.WHITE);
        firstCellsLabel.setFont(titleFont);



        firstCellsPanel.add(firstCellsLabel);
    }

    public static void fillCellSizePanel() {
        cellSizePanel = new JPanel();
        cellSizePanel.setLayout(null);
        cellSizePanel.setBounds(0, 350, 500, 150);
        cellSizePanel.setBackground(Color.BLACK);
        cellSizePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        
        
        
        cellSizeLabel = new JLabel("Cell size");
        cellSizeLabel.setBounds(10, 0, 200, 40);
        cellSizeLabel.setForeground(Color.WHITE);
        cellSizeLabel.setFont(titleFont);


        cellSizePanel.add(cellSizeLabel);
    }

    public static JFrame getOptionsFrame() {
        return optionsFrame;
    }
}
