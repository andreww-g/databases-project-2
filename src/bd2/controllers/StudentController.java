package bd2.controllers;

import bd2.App;
import bd2.controllers.dialogs.StudentPersonalDataController;
import bd2.models.LoginModel;
import bd2.models.StudentModel;
import bd2.tools.LoginTools;
import bd2.views.StudentView;
import bd2.views.dialogs.StudentPersonalDataView;

import javax.swing.*;
import java.sql.SQLException;
import java.time.DayOfWeek;

public class StudentController implements IController {
    private final StudentView view;
    private final StudentModel model;
    private StudentPersonalDataView studentPersonalDataView;

    public StudentController(StudentView view, LoginModel model) {
        this.view = view;
        this.model = new StudentModel(model);
        init();
    }

    @Override
    public void init() {
        showTimetable();
        view.getIdField().setText(LoginTools.getLoginIdText(this.model));
        view.getPersonalDataButton().addActionListener(e -> showPersonalData());
        view.getTimetableButton().addActionListener(e -> showTimetable());
        view.getGradesButton().addActionListener(e -> showGrades());
        view.getEnrollButton().addActionListener(e -> showRecords());
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
                var year = rs.getString("YEAR");
                var pesel = rs.getString("PESEL");
                var semester = rs.getString("SEMESTER");
                var specialization = rs.getString("SPECIALIZATION");
                var avg_grade = String.valueOf(rs.getDouble("AVG_GRADE"));
                StudentPersonalDataController ct = new StudentPersonalDataController(first_name, last_name, email, faculty, year, pesel, semester, specialization, avg_grade);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showTimetable() {
        view.setTableModel(1);

        try {
            var rs = model.getRecords();

            while (rs.next()) {
                var professor = rs.getString("PROFESSOR");
                var subject = rs.getString("SUBJECT_NAME");
                var day = rs.getInt("DAY");
                var day_string = DayOfWeek.of(day).toString();
                var day_string_formatted = day_string.substring(0, 1).toUpperCase() + day_string.substring(1).toLowerCase();
                var time = rs.getString("TIME");
                var parity = rs.getString("PARITY");
                var form = rs.getString("FORM");

                view.getTableModel().addRow(new String[]{day_string_formatted, time,
                        parity, subject, professor, form});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    private void showGrades() {
        view.setTableModel(2);

        try {
            var rs = model.getGrades();
            while (rs.next()) {
                var subject_name = rs.getString("SUBJECT_NAME");
                var form = rs.getString("FORM");
                var grade = rs.getString("GRADE");
                var professor = rs.getString("PROFESSOR");
                var total_months = rs.getInt("TOTAL_MONTHS");
                var year = String.valueOf(calcYear(total_months));
                var semester = String.valueOf(calcSemester(total_months));
                view.getTableModel().addRow(new String[]{year, semester, subject_name, form, grade, professor});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showRecords() {
        view.setTableModel(3);

        try {
            var rs = model.getRecords();
            while (rs.next()) {
                var professor = rs.getString("PROFESSOR");
                var subject_name = rs.getString("SUBJECT_NAME");
                var parity = rs.getString("PARITY");
                var time = rs.getString("TIME");
                var day = String.valueOf(rs.getInt("DAY"));
                var form = rs.getString("FORM");
                var student_limit = String.valueOf(rs.getInt("STUDENT_LIMIT"));
                var grade = rs.getString("GRADE");
                view.getTableModel().addRow(new String[]{professor, subject_name, parity, time, day, form, student_limit, grade});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private int calcYear(int months) {
        float year = months / 12f;
        if (year == 0)
            return 1;
        return (int) Math.ceil(year);
    }

    private int calcSemester(int months) {
        float sem = months / 6f;
        if (sem == 0)
            return 1;
        return (int) Math.ceil(sem);
    }
}
