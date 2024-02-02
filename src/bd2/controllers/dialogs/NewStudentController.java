package bd2.controllers.dialogs;

import bd2.models.dialogs.NewStudentModel;
import bd2.tools.ParseTools;
import bd2.views.dialogs.NewStudentView;

import java.sql.SQLException;

public class NewStudentController {
    private final NewStudentView view;
    private final String faculty_id;
    private NewStudentModel model;

    public NewStudentController(String faculty_id) {
        this.view = new NewStudentView();
        this.faculty_id = faculty_id;

        init();
        run();
    }

    private void init() {
        view.getOkButton().addActionListener(e -> getData());
        view.getCancelButton().addActionListener(e -> view.dispose());

        try {
            var rs = NewStudentModel.getSpecsOfFaculty(faculty_id);

            while (rs.next()) {
                var spec_name = rs.getString("NAME");
                var spec_id = rs.getInt("SPECIALIZATION_ID");

                view.getSpecializationCbx().addItem(String.format("%s (%d)", spec_name, spec_id));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void run() {
        view.setModal(true);
        view.pack();
        view.setVisible(true);
    }

    private void getData() {
        model = new NewStudentModel();

        model.firstName = view.getTxtFirstName().getText();
        model.lastName = view.getTxtLastName().getText();
        var specialization_name = (String) view.getSpecialization();
        model.specialization_id = ParseTools.extractBetween(specialization_name, '(', ')');
        model.pesel = view.getTxtPESEL().getText();

        view.dispose();
    }

    public NewStudentModel getModel() {
        return model;
    }
}
