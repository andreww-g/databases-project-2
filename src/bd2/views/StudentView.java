package bd2.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentView extends Component {

    private final JFrame frame;
    private final DefaultTableModel tableModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    };
    private JPanel studentPanel;
    private JScrollPane scrollPane;
    private JLabel loggedAsLabel;
    private JTable dataTable;
    private JTextField idField;
    private JButton enrollButton;
    private JButton gradesButton;
    private JButton timetableButton;
    private JButton personalDataButton;
    private JButton logOutButton;

    public StudentView() {
        frame = new JFrame("Student menu");
        frame.setContentPane(studentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        dataTable.setModel(tableModel);
        dataTable.getTableHeader().setReorderingAllowed(false);
        dataTable.getTableHeader().setResizingAllowed(false);
    }

    public void dispose() {
        frame.dispose();
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(int state) {
        tableModel.setRowCount(0);
        switch (state) {
            case 0 -> tableModel.setColumnIdentifiers(new String[]{"First name", "Last name", "Email", "Faculty", "PESEL", "Year", "Semester", "Specialization", "Average grade"});
            case 1 -> tableModel.setColumnIdentifiers(new String[]{"Day", "Time", "Parity", "Subject", "Professor", "Form"});
            case 2 -> tableModel.setColumnIdentifiers(new String[]{"Year", "Semester", "Subject", "Form", "Grade", "Professor"});
            case 3 -> tableModel.setColumnIdentifiers(new String[]{"Professor", "Subject", "Parity", "Time", "Day", "Form", "Student limit", "Grade"});
        }
    }

    public JTextField getIdField() {
        return idField;
    }

    public JButton getTimetableButton() {
        return timetableButton;
    }

    public JButton getPersonalDataButton() {
        return personalDataButton;
    }

    public JButton getGradesButton() {
        return gradesButton;
    }

    public JButton getEnrollButton() {
        return enrollButton;
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }
}