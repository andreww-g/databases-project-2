package bd2.controllers.dialogs;

import bd2.views.dialogs.ProfessorPersonalDataView;

public class ProfessorPersonalDataController {
    private final ProfessorPersonalDataView view;

    public ProfessorPersonalDataController() {
        this.view = new ProfessorPersonalDataView();
        init();
    }

    public ProfessorPersonalDataController(String first_name, String last_name, String email, String faculty, String degree) {
        this.view = new ProfessorPersonalDataView();
        init(first_name, last_name, email, faculty, degree);
    }

    public void init() {
        view.getCloseButton().addActionListener(e -> view.dispose());
        view.setModal(true);
        view.pack();
        view.setVisible(true);
    }

    private void init(String first_name, String last_name, String email, String faculty, String degree) {
        view.getFirstNameField().setText(first_name);
        view.getLastNameField().setText(last_name);
        view.getEmailField().setText(email);
        view.getFacultyField().setText(faculty);
        view.getDegreeField().setText(degree);
        init();
    }
}