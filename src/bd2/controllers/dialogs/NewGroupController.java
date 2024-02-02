package bd2.controllers.dialogs;

import bd2.controllers.IController;
import bd2.models.dialogs.NewGroupModel;
import bd2.tools.ParseTools;
import bd2.views.dialogs.NewGroupView;

import java.sql.SQLException;
import java.time.DayOfWeek;


public class NewGroupController implements IController {
    private final NewGroupView view;
    private final String faculty_id;
    private NewGroupModel model;

    public NewGroupController(String faculty_id) {
        this.view = new NewGroupView();
        this.faculty_id = faculty_id;

        init();
        run();
    }

    public NewGroupController(String faculty_id, String group_id, String subject_id, String professor_id, String parity, String day, String time, String form, String student_limit) {
        this.view = new NewGroupView();
        this.faculty_id = faculty_id;

        init(group_id, subject_id, professor_id, parity, day, time, form, student_limit);
        run();
    }

    public void init() {
        view.getOkButton().addActionListener(e -> getData());
        view.getCancelButton().addActionListener(e -> view.dispose());

        try {
            var rs = NewGroupModel.getSubjectsOfFaculty(faculty_id);
            while (rs.next()) {
                var subject_name = rs.getString("SUBJECT_NAME");
                var subject_id = rs.getInt("SUBJECT_ID");

                view.getSubjectComboBox().addItem(String.format("%s (%d)", subject_name, subject_id));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        view.getSubjectComboBox().setSelectedItem(null);

        for (var day : DayOfWeek.values()) {
            view.getDayComboBox().addItem(day.toString());
        }
    }

    @Override
    public void dispose() {
        view.dispose();
    }

    private void init(String group_id, String subject_id, String professor_id, String parity, String day, String time, String form, String student_limit) {
        init();

        view.getTxtGroupID().setText(group_id);
        view.getTxtProfessorID().setText(professor_id);
        view.getTxtParity().setText(parity);
        var dayOfWeek = DayOfWeek.of(Integer.parseInt(day)).toString();
        view.getDayComboBox().setSelectedItem(dayOfWeek);
        view.getTxtTime().setText(time);
        view.getTxtForm().setText(form);
        view.getTxtStudentLimit().setText(student_limit);

        String subject_item = null;
        try {
            subject_item = String.format(NewGroupModel.findSubjectNameById(Integer.parseInt(subject_id)) + " (%s)", subject_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        view.getSubjectComboBox().setSelectedItem(subject_item);
    }

    private void run() {
        view.setModal(true);
        view.pack();
        view.setVisible(true);
    }

    public NewGroupModel getModel() {
        return model;
    }

    private void getData() {
        model = new NewGroupModel();

        model.group_id = Integer.parseInt(view.getTxtGroupID().getText());
        model.professor_id = Integer.parseInt(view.getTxtProfessorID().getText());
        var subject_name = (String) view.getSubjectComboBoxItem();
        model.subject_id = Integer.parseInt(ParseTools.extractBetween(subject_name, '(', ')'));
        model.parity = view.getTxtParity().getText();
        model.day = DayOfWeek.valueOf(view.getDayComboBoxItem()).getValue();
        model.time = view.getTxtTime().getText();
        model.form = view.getTxtForm().getText();
        model.student_limit = Integer.parseInt(view.getTxtStudentLimit().getText());

        view.dispose();
    }
}
