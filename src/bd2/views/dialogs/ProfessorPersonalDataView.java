package bd2.views.dialogs;

import javax.swing.*;

public class ProfessorPersonalDataView extends JDialog {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField facultyField;
    private JTextField getDegreeField;
    private JPanel professorPersonalDataPanel;
    private JButton closeButton;

    public ProfessorPersonalDataView() {
        setTitle("Personal Data");
        setContentPane(professorPersonalDataPanel);
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

    public JTextField getDegreeField() {
        return getDegreeField;
    }

    public JPanel getProfessorPersonalDataPanel() {
        return professorPersonalDataPanel;
    }
}
