import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AdvancedOptions {
    private static JFrame optionsFrame;
    private static JPanel colorPanel, firstCellsPanel, cellSizePanel;


    // color panel stuff
    private static JLabel colorLabel, chooseColorModeLabel, chooseColorLabel, oneGenColorLabel;
    private static JComboBox<Object> colorModeBox;
    private static ColorMode[] colorModes = {ColorMode.Normal, ColorMode.Rainbow, ColorMode.Random, ColorMode.Specific};
    private static JCheckBox oneGenColorCheckBox;

    private static ColorMode colorMode = ColorMode.Normal;
    private static boolean oneGenerationColor = false;


    // first cell panel stuff
    private static JLabel firstCellsLabel, firstCellPatternLabel, percRandomLabel;
    private static JComboBox<Object> cellModeBox;
    private static FirstCellMode[] cellModes = { FirstCellMode.Random, FirstCellMode.Line, FirstCellMode.Empty };
    private static JSpinner percRandomSpinner;
    private static SpinnerNumberModel firstCellsSpinnerModel = new SpinnerNumberModel(50, 1, 100, 5);

    private static FirstCellMode firstCellMode = FirstCellMode.Random;
    private static int percOfAliveCells = 40;


    // cell size panel stuff
    private static JLabel cellSizeLabel, setCellSizeLabel;
    private static JSpinner cellSizeSpinner;
    private static SpinnerNumberModel cellSizeSpinnerModel = new SpinnerNumberModel(5, 1, 50, 1);


    private static Font titleFont = new Font(null, Font.BOLD, 20);
    private static Font normalFont = new Font(null, Font.PLAIN, 16);
    
    public AdvancedOptions() {
        optionsFrame = new JFrame();
        optionsFrame.setTitle("Options");
        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionsFrame.setBackground(Color.BLACK);
        optionsFrame.setSize(500 + 16, 500 + 39);
        optionsFrame.setLayout(null);
        optionsFrame.setResizable(false);

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

        chooseColorModeLabel = new JLabel("Choose a color mode:");
        chooseColorModeLabel.setBounds(10, 50, 200, 30);
        chooseColorModeLabel.setForeground(Color.WHITE);
        chooseColorModeLabel.setFont(normalFont);
        
        chooseColorLabel = new JLabel("Choose a Color:");
        chooseColorLabel.setBounds(10, 90, 200, 30);
        chooseColorLabel.setForeground(Color.WHITE);
        chooseColorLabel.setFont(normalFont);
        
        oneGenColorLabel = new JLabel("One color per generation:");
        oneGenColorLabel.setBounds(10, 130, 200, 30);
        oneGenColorLabel.setForeground(Color.WHITE);
        oneGenColorLabel.setFont(normalFont);

        colorModeBox = new JComboBox<Object>(colorModes);
        colorModeBox.setBounds(300, 50, 100, 30);
        colorModeBox.addActionListener(e -> {
            colorMode = (ColorMode) colorModeBox.getSelectedItem();
        });

        oneGenColorCheckBox = new JCheckBox();
        oneGenColorCheckBox.setBounds(300, 130, 100, 30);
        oneGenColorCheckBox.setBackground(Color.BLACK);
        oneGenColorCheckBox.addActionListener(e -> {
            if (oneGenColorCheckBox.isSelected()) {
                oneGenerationColor = true;
            } else {
                oneGenerationColor = false;
            }
        });


        colorPanel.add(colorLabel);
        colorPanel.add(chooseColorModeLabel);
        colorPanel.add(chooseColorLabel);
        colorPanel.add(oneGenColorLabel);
        colorPanel.add(colorModeBox);
        colorPanel.add(oneGenColorCheckBox);
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

        firstCellPatternLabel = new JLabel("Pattern for first cells:");
        firstCellPatternLabel.setBounds(10, 50, 200, 30);
        firstCellPatternLabel.setForeground(Color.WHITE);
        firstCellPatternLabel.setFont(normalFont);

        percRandomLabel = new JLabel("% of alive cells when using random:");
        percRandomLabel.setBounds(10, 90, 250, 30);
        percRandomLabel.setForeground(Color.WHITE);
        percRandomLabel.setFont(normalFont);

        cellModeBox = new JComboBox<Object>(cellModes);
        cellModeBox.setBounds(300, 50, 100, 30);
        cellModeBox.addActionListener(e -> {
            firstCellMode = (FirstCellMode) cellModeBox.getSelectedItem();
            // BasicOptions.reset();
        });

        percRandomSpinner = new JSpinner(firstCellsSpinnerModel);
        percRandomSpinner.setBounds(300, 90, 100, 30);
        percRandomSpinner.addChangeListener(e -> {
            percOfAliveCells = (int) percRandomSpinner.getValue();
        });


        firstCellsPanel.add(firstCellPatternLabel);
        firstCellsPanel.add(percRandomLabel);
        firstCellsPanel.add(cellModeBox);
        firstCellsPanel.add(percRandomSpinner);
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


        setCellSizeLabel = new JLabel("Set size of cells:");
        setCellSizeLabel.setBounds(10, 50, 200, 30);
        setCellSizeLabel.setForeground(Color.WHITE);
        setCellSizeLabel.setFont(normalFont);

        cellSizeSpinner = new JSpinner(cellSizeSpinnerModel);
        cellSizeSpinner.setBounds(300, 50, 100, 30);
        cellSizeSpinner.addChangeListener(e -> {
            Cell.setCellWidth((int) cellSizeSpinner.getValue());
            Cell.setCellHeight((int) cellSizeSpinner.getValue());
            // BasicOptions.reset();
        });


        cellSizePanel.add(setCellSizeLabel);
        cellSizePanel.add(cellSizeSpinner);
        cellSizePanel.add(cellSizeLabel);
    }

    public static JFrame getOptionsFrame() {
        return optionsFrame;
    }

     public static ColorMode getColorMode() {
        return colorMode;
    }

    public static boolean isOneGenerationColor() {
        return oneGenerationColor;
    }


    public static FirstCellMode getAliveCellMode() {
        return firstCellMode;
    }

    public static float getPercOfAliveCells() {
        return percOfAliveCells;
    }
}
