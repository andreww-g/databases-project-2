package bd2.controllers;

import bd2.App;
import bd2.controllers.dialogs.ProfessorPersonalDataController;
import bd2.models.LoginModel;
import bd2.models.ProfessorModel;
import bd2.tools.LoginTools;
import bd2.views.ProfessorView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class ProfessorController implements IController {
    private final ProfessorView view;
    private final ProfessorModel model;
    MouseListener mouseListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent me) {
            if (me.getClickCount() == 2) {
                JTable target = (JTable) me.getSource();
                int row = target.getSelectedRow();
                if (row >= 0) {
                    String string_group_id = (String) view.dataTable.getValueAt(row, 0);
                    int clicked_group_id = Integer.parseInt(string_group_id);
                    showStudents(clicked_group_id);
                }
            }
        }
    };

    public ProfessorController(ProfessorView view, LoginModel model) {
        this.view = view;
        this.model = new ProfessorModel(model);
        init();
    }

    @Override
    public void init() {
        showTimetable();
        view.getIdField().setText(LoginTools.getLoginIdText(this.model));
        view.getPersonalDataButton().addActionListener(e -> showPersonalData());
        view.getTimetableButton().addActionListener(e -> showTimetable());
        view.getGroupsButton().addActionListener(e -> showGroups());
        view.getSaveButton().addActionListener(e -> updateGrades());
        view.getLogOutButton().addActionListener(e -> dispose());
    }

    @Override
    public void dispose() {
        view.dispose();
        App.reconnect();
        new LoginController();
    }

    private void showPersonalData() {
        try {
            var rs = model.getPersonalData();
            while (rs.next()) {
                var first_name = rs.getString("FIRST_NAME");
                var last_name = rs.getString("LAST_NAME");
                var email = rs.getString("EMAIL");
                var faculty = rs.getString("FACULTY");
                var degree = rs.getString("DEGREE");
                ProfessorPersonalDataController ct = new ProfessorPersonalDataController(first_name, last_name, email, faculty, degree);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showTimetable() {
        view.dataTable.setModel(view.tableModel);
        view.saveButton.setVisible(false);
        view.getTableModel().setRowCount(0);
        view.getTableModel().setColumnIdentifiers(new String[]{"Day", "Time", "Parity", "Subject", "Form", "Students limit"});
        try {
            var rs = model.getGroups();
            while (rs.next()) {
                var day = rs.getString("DAY");
                var time = rs.getString("TIME");
                var parity = rs.getString("PARITY");
                var subject_name = rs.getString("SUBJECT_NAME");
                var form = rs.getString("FORM");
                var student_limit = rs.getString("STUDENT_LIMIT");
                view.getTableModel().addRow(new String[]{day, time, parity, subject_name, form, student_limit});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showGroups() {
        view.dataTable.setModel(view.tableModel);
        view.dataTable.addMouseListener(mouseListener);
        view.saveButton.setVisible(false);
        view.getTableModel().setRowCount(0);
        view.getTableModel().setColumnIdentifiers(new String[]{"Group ID", "Subject ID", "Subject name", "Day", "Time", "Parity", "Form"});
        try {
            var rs = model.getGroups();
            while (rs.next()) {
                var group_id = rs.getString("GROUP_ID");
                var subject_id = rs.getString("SUBJECT_ID");
                var subject_name = rs.getString("SUBJECT_NAME");
                var day = rs.getString("DAY");
                var time = rs.getString("TIME");
                var parity = rs.getString("PARITY");
                var form = rs.getString("FORM");
                view.getTableModel().addRow(new String[]{group_id, subject_id, subject_name, day, time, parity, form});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showStudents(int clicked_group_id) {
        view.dataTable.removeMouseListener(mouseListener);
        view.dataTable.repaint();
        view.getTableModel2().setRowCount(0);
        view.dataTable.setModel(view.tableModel2);
        view.saveButton.setVisible(true);
        view.getTableModel2().setColumnIdentifiers(new String[]{"Student ID", "Group ID", "Grade", "Student name", "Email"});
        try {
            var rs = model.getStudents(clicked_group_id);
            while (rs.next()) {
                var student_id = rs.getString("STUDENT_ID");
                var group_id = rs.getString("GROUP_ID");
                var student = rs.getString("STUDENT");
                var email = rs.getString("EMAIL");
                var grade = rs.getString("GRADE");
                view.getTableModel2().addRow(new String[]{student_id, group_id, grade, student, email});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void updateGrades() {
        view.dataTable.setModel(view.tableModel2);
        try {
            var rs = model.getStudentsGrades();
            while (rs.next()) {
                int row = view.dataTable.getSelectedRow();
                if (row >= 0) {
                    String string_student_id = (String) view.tableModel2.getValueAt(row, 0);
                    String string_group_id = (String) view.tableModel2.getValueAt(row, 1);
                    String string_grade = (String) view.tableModel2.getValueAt(row, 2);
                    int student_id = Integer.parseInt(string_student_id);
                    int group_id = Integer.parseInt(string_group_id);
                    Double grade = null;
                    if (string_grade != null && !string_grade.equals("")) {
                        grade = Double.parseDouble(string_grade);
                    }
                    model.addGrade(student_id, group_id, grade);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}