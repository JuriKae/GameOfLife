import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
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
    private static ColorMode[] colorModes = { ColorMode.Normal, ColorMode.Rainbow, ColorMode.Random,
            ColorMode.Specific };
    private static JCheckBox oneGenColorCheckBox;
    private static JPanel chosenColorPanel;
    private static Color chosenColor = Color.WHITE;

    private static ColorMode colorMode = ColorMode.Normal;
    private static boolean oneGenerationColor = false;

    private static boolean colorsSwitched = false;

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
    private static Font boxFont = new Font(null, Font.BOLD, 14);

    private static Color buttonColor = new Color(255, 255, 255);
    private static Color boxFontColor = Color.BLACK;

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
        colorModeBox.setFocusable(false);
        colorModeBox.setBounds(300, 50, 100, 30);
        colorModeBox.setBackground(buttonColor);
        colorModeBox.setForeground(boxFontColor);
        colorModeBox.setFont(boxFont);
        colorModeBox.addActionListener(e -> {
            colorMode = (ColorMode) colorModeBox.getSelectedItem();

            // directly change mode when color mode is changed
            for (int i = 1; i < Cell.getxGrids() - 1; i++) {
                for (int j = 1; j < Cell.getyGrids() - 1; j++) {
                    if (!oneGenerationColor) {
                        CellColor.handleCellColor(Cell.getCells()[i][j]);
                    } else {
                        CellColor.handleGenerationColor(Cell.getCells()[i][j]);
                    }
                }
            }
            Main.getMain().repaint();
        });

        chosenColorPanel = new JPanel();
        chosenColorPanel.setBounds(300, 90, 23, 23);
        chosenColorPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        chosenColorPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                chosenColor = JColorChooser.showDialog(null, "Choose a color", null);
                chosenColorPanel.setBackground(chosenColor);
                colorMode = ColorMode.Specific;
                colorModeBox.setSelectedItem(ColorMode.Specific);
            }
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

            // directly change mode when color mode is changed
            for (int i = 1; i < Cell.getxGrids() - 1; i++) {
                for (int j = 1; j < Cell.getyGrids() - 1; j++) {
                    if (!oneGenerationColor) {
                        CellColor.handleCellColor(Cell.getCells()[i][j]);
                    } else {
                        CellColor.handleGenerationColor(Cell.getCells()[i][j]);
                    }
                }
            }
            Main.getMain().repaint();
        });


        colorPanel.add(colorLabel);
        colorPanel.add(chooseColorModeLabel);
        colorPanel.add(chooseColorLabel);
        colorPanel.add(oneGenColorLabel);
        colorPanel.add(chosenColorPanel);
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
        cellModeBox.setFocusable(false);
        cellModeBox.setBounds(300, 50, 100, 30);
        cellModeBox.setBackground(buttonColor);
        cellModeBox.setForeground(boxFontColor);
        cellModeBox.setFont(boxFont);
        cellModeBox.addActionListener(e -> {
            firstCellMode = (FirstCellMode) cellModeBox.getSelectedItem();
            // BasicOptions.reset();
        });

        percRandomSpinner = new JSpinner(firstCellsSpinnerModel);
        percRandomSpinner.setBounds(300, 90, 100, 30);
        percRandomSpinner.getEditor().getComponent(0).setBackground(buttonColor);
        percRandomSpinner.getEditor().getComponent(0).setForeground(boxFontColor);
        percRandomSpinner.setFont(boxFont);
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
        cellSizeSpinner.getEditor().getComponent(0).setBackground(buttonColor);
        cellSizeSpinner.getEditor().getComponent(0).setForeground(boxFontColor);
        cellSizeSpinner.setFont(boxFont);
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

    public static Color getChosenColor() {
        return chosenColor;
    }

    public static boolean isColorsSwitched() {
        return colorsSwitched;
    }
}