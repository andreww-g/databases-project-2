package bd2.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class WorkerView extends Component {
    private final JFrame frame;
    private final DefaultTableModel tableModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    };
    private JPanel workerPanel;
    private JTextField idField;
    private JButton logOutButton;
    private JScrollPane scrollPane;
    private JLabel loggedAsLabel;
    private JButton studentsButton;
    private JButton groupsButton;
    private JTable dataTable;
    private JButton removeButton;
    private JButton editButton;
    private JButton addButton;

    public WorkerView() {
        frame = new JFrame("Worker menu");
        frame.setContentPane(workerPanel);
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

    public JTable getDataTable() {
        return dataTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(int i) {
        tableModel.setRowCount(0);
        switch (i) {
            case 0 -> tableModel.setColumnIdentifiers(new String[]{"Student ID", "First name", "Last name", "Email", "Faculty", "PESEL", "Year", "Semester", "Specialization"});
            //case 1 -> tableModel.setColumnIdentifiers(new String[]{"Group ID", "Subject ID", "Subject name", "Day", "Time", "Parity", "Form"});
            case 1 -> tableModel.setColumnIdentifiers(new String[]{"Group ID", "Subject ID", "Subject name", "Professor ID", "Day", "Time", "Parity", "Form", "Student limit"});
            case 2 -> tableModel.setColumnIdentifiers(new String[]{"Student ID", "Student", "Email", "Grade", "Record date"});
        }
    }

    public JTextField getIdField() {
        return idField;
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }

    public JButton getStudentsButton() {
        return studentsButton;
    }

    public JButton getGroupsButton() {
        return groupsButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getAddButton() {
        return addButton;
    }
}
