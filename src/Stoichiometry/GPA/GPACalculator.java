package Stoichiometry.GPA;

/*
 * Khoi Bui
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class GPACalculator extends JFrame {
    private JLabel gradeL, unitsL, emptyL;

    private JTextField gradeTF, unitsTF;

    private JButton addB, gpaB, resetB, exitB;

    private AddButtonHandler abHandler;
    private GPAButtonHandler cbHandler;
    private ResetButtonHandler rbHandler;
    private ExitButtonHandler ebHandler;

    private static final int WIDTH = 400;
    private static final int HEIGHT = 150;

    private static double totalUnits = 0.0; // total units taken
    private static double gradePoints = 0.0; // total grade points from those units
    private static double totalGPA = 0.0; // total GPA (gradePoints / totalUnits)

    // Constructor
    public GPACalculator() {
        // Create labels
        gradeL = new JLabel("Enter your grade: ", SwingConstants.RIGHT);
        unitsL = new JLabel("Enter number of units: ", SwingConstants.RIGHT);
        emptyL = new JLabel("", SwingConstants.RIGHT);

        // Create text fields
        gradeTF = new JTextField(10);
        unitsTF = new JTextField(10);

        // Create Add Button
        addB = new JButton("ADD");
        abHandler = new AddButtonHandler();
        addB.addActionListener(abHandler);

        // Create GPA Button
        gpaB = new JButton("GPA");
        cbHandler = new GPAButtonHandler();
        gpaB.addActionListener(cbHandler);

        // Create Reset Button
        resetB = new JButton("Reset");
        rbHandler = new ResetButtonHandler();
        resetB.addActionListener(rbHandler);

        // Create Exit Button
        exitB = new JButton("Exit");
        ebHandler = new ExitButtonHandler();
        exitB.addActionListener(ebHandler);

        // Set the title of the window
        setTitle("GPA Calculator");

        // Get the container
        Container pane = getContentPane();

        // Set the layout
        // 3 rows, 3 columns
        pane.setLayout(new GridLayout(3, 3));

        // Place the components in the pane
        pane.add(gradeL);
        pane.add(gradeTF);
        pane.add(addB);
        pane.add(unitsL);
        pane.add(unitsTF);
        pane.add(gpaB);
        pane.add(emptyL);
        pane.add(resetB);
        pane.add(exitB);

        // Set the size of the window and display it
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class AddButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // If there are no entries, display message
            if (gradeTF.getText().equals("") && unitsTF.getText().equals("")) {
                noEntryMsg();
            } else {
                try {
                    // Read the score and the units
                    char grade = gradeTF.getText().charAt(0);
                    double units = Double.parseDouble(unitsTF.getText()); // This can throw a NumberFormatException
                    // Catch it!

                    double points = 0.0;

                    // Calculate points according to the entries (grade & units)
                    // For example:
                    // When grade is 'a' or 'A', the points should be units * 4.0
                    // When grade is 'b' or 'B', the points should be units * 3.0
                    // etc...
                    // When grade is 'f' or 'F', the points should be 0.0
                    // If the grade is not A-D or F, set both the points and the units to 0.0
                    // and show a message dialog box informing the user of the error.
                    // Your code here (1 of 5)....
                    switch (Character.toUpperCase(grade)) {
                        case 'A': {
                            points = 4.0 * units;
                            break;
                        }
                        case 'B': {
                            points = 3.0 * units;
                            break;
                        }
                        case 'C': {
                            points = 2.0 * units;
                            break;
                        }
                        case 'D': {
                            points = 1.0 * units;
                            break;
                        }
                        case 'F': {
                            points = 0.0 * units;
                            break;
                        }
                        default: {
                            points = 0.0;
                            units = 0.0;
                            String msg = "Invalid grade!";
                            JOptionPane.showMessageDialog(null, msg, "Result",
                                    JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                    // Add units to total units
                    // Add points to total grade points
                    // Your code here (2 of 5)....
                    totalUnits += units;
                    gradePoints += points;
                } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
                    String msg = "Invalid grade or unit!";
                    JOptionPane.showMessageDialog(null, msg, "Result",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                // Reset text fields
                gradeTF.setText("");
                unitsTF.setText("");
            }
        }
    }

    private class ResetButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String msg = "Resetting will delete all entries.\n" +
                    "Are you sure you want to continue?";

            int answer = JOptionPane.showConfirmDialog(null,
                    msg, "Click Yes of No", JOptionPane.YES_NO_OPTION);

            // Display window to try again
            if (answer == JOptionPane.YES_OPTION) {
                // Reset text fields
                gradeTF.setText("");
                unitsTF.setText("");

                // Reset total units and total grade points
                // Your code here (3 of 5)....
                totalUnits = 0.0;
                gradePoints = 0.0;
            }

        }
    }

    private class GPAButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (gradePoints == 0.0) {
                if (gradeTF.getText().equals("") && unitsTF.getText().equals("")) {
                    noEntryMsg();
                } else {
                    String msg = "Click the ADD button\n and then click GPA again.";

                    // Show results on message box
                    JOptionPane.showMessageDialog(null, msg, "Result",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                // Compute totalGPA by dividing the total grade points by the total units
                // Your code here (4 of 5)....
                totalGPA = gradePoints / totalUnits;

                DecimalFormat df = new DecimalFormat("0.000");

                String outputStr = "GPA: " + df.format(totalGPA) + "\n";

                // Show results on message box
                JOptionPane.showMessageDialog(null, outputStr, "Result",
                        JOptionPane.INFORMATION_MESSAGE);

                int answer = JOptionPane.showConfirmDialog(null,
                        "Try again?", "Click Yes of No",
                        JOptionPane.YES_NO_OPTION);

                // Display window to try again
                if (answer == JOptionPane.YES_OPTION) {
                    // Reset text fields
                    gradeTF.setText("");
                    unitsTF.setText("");

                    // Reset total units and total grade points
                    // Your code here (5 of 5)....

                    totalUnits = 0.0;
                    gradePoints = 0.0;
                } else if (answer == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }
        }
    }

    private class ExitButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void noEntryMsg() {
        String msg = "Enter grade and number of units.";
        // Show results on message box
        JOptionPane.showMessageDialog(null, msg, "Result",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        GPACalculator gpaCalculator = new GPACalculator();
    }
}
