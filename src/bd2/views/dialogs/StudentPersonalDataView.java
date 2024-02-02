package bd2.views.dialogs;

import javax.swing.*;

public class StudentPersonalDataView extends JDialog {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField facultyField;
    private JTextField peselField;
    private JTextField yearField;
    private JTextField semesterField;
    private JTextField specializationField;
    private JTextField averageGradeField;
    private JPanel studentPersonalDataPanel;
    private JButton closeButton;

    public StudentPersonalDataView() {
        setTitle("Personal Data");
        setContentPane(studentPersonalDataPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(closeButton);
        setLocationRelativeTo(null);
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getFacultyField() {
        return facultyField;
    }

    public JTextField getPeselField() {
        return peselField;
    }

    public JTextField getYearField() {
        return yearField;
    }

    public JTextField getSemesterField() {
        return semesterField;
    }

    public JTextField getSpecializationField() {
        return specializationField;
    }

    public JTextField getAverageGradeField() {
        return averageGradeField;
    }

}
