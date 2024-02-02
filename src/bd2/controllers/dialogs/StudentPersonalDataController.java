package bd2.controllers.dialogs;

import bd2.views.dialogs.StudentPersonalDataView;

public class StudentPersonalDataController {
    private final StudentPersonalDataView view;

    public StudentPersonalDataController() {
        this.view = new StudentPersonalDataView();
        init();
    }

    public StudentPersonalDataController(String first_name, String last_name, String email, String faculty, String year, String pesel, String semester, String specialization, String avg_grade) {
        this.view = new StudentPersonalDataView();
        init(first_name, last_name, email, faculty, year, pesel, semester, specialization, avg_grade);
    }

    public void init() {
        view.getCloseButton().addActionListener(e -> view.dispose());
        view.setModal(true);
        view.pack();
        view.setVisible(true);
    }

    private void init(String first_name, String last_name, String email, String faculty, String year, String pesel, String semester, String specialization, String avg_grade) {
        view.getFirstNameField().setText(first_name);
        view.getLastNameField().setText(last_name);
        view.getEmailField().setText(email);
        view.getFacultyField().setText(faculty);
        view.getYearField().setText(year);
        view.getPeselField().setText(pesel);
        view.getSemesterField().setText(semester);
        view.getSpecializationField().setText(specialization);
        view.getAverageGradeField().setText(avg_grade);
        init();
    }
}