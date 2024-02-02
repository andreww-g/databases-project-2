package bd2.views.dialogs;

import javax.swing.*;

public class NewStudentView extends JDialog {
    public JButton okButton;
    public JButton cancelButton;
    public JTextField txtFirstName;
    public JTextField txtLastName;
    public JTextField txtPESEL;
    private JPanel newStudentPanel;
    private JComboBox<String> specializationCbx;

    public NewStudentView() {
        setTitle("Student menu");
        setContentPane(newStudentPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(okButton);
        setLocationRelativeTo(null);
    }

    public Object getSpecialization() {
        return specializationCbx.getSelectedItem();
    }

    public JComboBox<String> getSpecializationCbx() {
        return specializationCbx;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JTextField getTxtFirstName() {
        return txtFirstName;
    }

    public JTextField getTxtLastName() {
        return txtLastName;
    }

    public JTextField getTxtPESEL() {
        return txtPESEL;
    }
}
