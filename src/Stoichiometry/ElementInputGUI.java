package Stoichiometry;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.FlowLayout;


public class ElementInputGUI extends JFrame implements ActionListener {

    private JTextField elementField, quantityField;
    private JButton addButton, calculateButton, resetButton, deleteButton;
    private JTextArea inputArea, outputArea;    
    private ElementList elementList;
    private Element element;

    public ElementInputGUI() {
        elementList = new ElementList();
        buildGUI();
    }

    private void buildGUI() {
        // Set up main window
        setTitle("Element Input");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        ImageIcon image = new ImageIcon("C:/Users/ironm/OneDrive/Desktop/Java Library/Chemistry/Mole.jpg");
        setIconImage(image.getImage());

        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel elementLabel = new JLabel("Element:");
        elementField = new JTextField();
        JLabel quantityLabel = new JLabel("Moles:");
        quantityField = new JTextField();
        addButton = new JButton("Add Element");
        addButton.addActionListener(this);
        deleteButton = new JButton("Delete Element");
        deleteButton.addActionListener(this);
        inputPanel.add(elementLabel);
        inputPanel.add(elementField);
        inputPanel.add(quantityLabel);
        inputPanel.add(deleteButton);
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        // Create input/output area panel
        JPanel ioPanel = new JPanel(new GridLayout(1, 2));
        inputArea = new JTextArea();
        inputArea.setEditable(false);
        JScrollPane inputScrollPane = new JScrollPane(inputArea);
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        ioPanel.add(inputScrollPane);
        ioPanel.add(outputScrollPane);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        buttonPanel.add(calculateButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(deleteButton);

        // Add components to main window
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(ioPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String temp = elementField.getText().trim();
            String chemical = temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase();
            String quantityStr = quantityField.getText().trim();
            Double quantity;

            try {
                quantity = Double.parseDouble(quantityStr);
            } catch (IllegalArgumentException | StringIndexOutOfBoundsException ex) {
                if (quantityStr.equals("")) {
                    JOptionPane.showMessageDialog(this, "Invalid quantity: Empty", "Error", JOptionPane.ERROR_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(this, "Invalid quantity: " + quantityStr, "Error",
                            JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                if (Element.findIndex(List.getSymbolArray(), chemical) == -1 || chemical.equals(null))
                    JOptionPane.showMessageDialog(this, "Invalid element", "Error", JOptionPane.ERROR_MESSAGE);
                else {
                    element = new Element(chemical, quantity);
                    String[] name = List.getNameArray();
                    int atomicNumber = Element.findIndex(List.getSymbolArray(), chemical);
                    elementList.addElement(element);
                    inputArea.append("Name: " + name[atomicNumber] + "\n" + "Symbol: " + chemical + "\n" + "Moles: " + quantity + "\n" + "\n");
                    elementField.setText("");
                    quantityField.setText("");
                }
            } catch (IllegalArgumentException | StringIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == calculateButton) {
            try {
                double molarMass = elementList.getMolarMassEL();
                String output = String.format("Total mass: %.3f g/mol", molarMass);
                outputArea.setText(output);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == resetButton) {
            elementList = new ElementList();
            inputArea.setText("");
            outputArea.setText("");
        } else if (e.getSource() == deleteButton){
            if (elementList.size() == 0){
                JOptionPane.showMessageDialog(this, "There is no element to delete", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                elementList.remove();
          }
        }
    }

    public static void main(String[] args) {
        ElementInputGUI gui = new ElementInputGUI();
        gui.setVisible(true);
    }
}