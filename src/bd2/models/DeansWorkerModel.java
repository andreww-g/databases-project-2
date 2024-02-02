package bd2.models;

import bd2.App;
import bd2.tools.LoginTools;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DeansWorkerModel extends UserModel {
    private String faculty_id;

    public DeansWorkerModel(LoginModel loginModel) {
        super(loginModel.getId(), loginModel.getType());

        try {
            var rs = getPersonalData();
            if (rs.next())
                faculty_id = rs.getString("FACULTY_ID");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editGroup(
            int group_id,
            int subject_id,
            int professor_id,
            String parity,
            int day,
            String time,
            String form,
            int student_limit
    ) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call SYS.MODIFY_GROUP(?, ?, ?, ?, ?, ?, ?, ?)}");
        stmt.setInt(1, group_id);
        stmt.setInt(2, subject_id);
        stmt.setInt(3, professor_id);
        stmt.setString(4, parity);
        stmt.setInt(5, day);
        stmt.setString(6, time);
        stmt.setString(7, form);
        stmt.setInt(8, student_limit);
        stmt.execute();
    }

    public void addGroup(
            int group_id,
            int subject_id,
            int professor_id,
            String parity,
            int day,
            String time,
            String form,
            int student_limit
    ) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call SYS.ADD_GROUP(?, ?, ?, ?, ?, ?, ?, ?)}");
        stmt.setInt(1, group_id);
        stmt.setInt(2, subject_id);
        stmt.setInt(3, professor_id);
        stmt.setString(4, parity);
        stmt.setInt(5, day);
        stmt.setString(6, time);
        stmt.setString(7, form);
        stmt.setInt(8, student_limit);
        stmt.execute();
    }

    public void addStudent(
            String first_name,
            String last_name,
            String faculty_id,
            String specialization_id,
            String pesel
    ) throws SQLException, NoSuchAlgorithmException {
        CallableStatement stmt = App.cn.prepareCall("{call SYS.ADD_STUDENT(?, ?, ?, ?, ?, ?, ?)}");

        var randLogin = LoginTools.randomString(10);
        var randPassword = LoginTools.randomPassword(10);

        stmt.setString(1, first_name);
        stmt.setString(2, last_name);
        stmt.setString(3, faculty_id);
        stmt.setString(4, specialization_id);
        stmt.setString(5, pesel);
        stmt.setString(6, randLogin);
        stmt.setString(7, randPassword);
        stmt.execute();
    }


    public ResultSet getStudentsFromGroup(int group_id) throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery("select * from SYS.GROUP_STUDENTS_VIEW where group_id = " + group_id);
    }

    public ResultSet getStudents() throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery("select * from SYS.STUDENT_VIEW where faculty_id = '" + faculty_id + "'");
    }

    public ResultSet getGroups() throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery("select * from SYS.GROUP_VIEW where faculty_id = '" + faculty_id + "'");
    }

    public void addStudentToGroup(int student_id, int group_id) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call SYS.ADD_STUDENT_TO_GROUP(?, ?)}");
        stmt.setInt(1, student_id);
        stmt.setInt(2, group_id);
        stmt.execute();
    }

    public void removeGroup(int group_id) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call SYS.REMOVE_GROUP(?)}");
        stmt.setInt(1, group_id);
        stmt.execute();
    }

    public void removeStudent(int student_id) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call SYS.REMOVE_STUDENT(?)}");
        stmt.setInt(1, student_id);
        stmt.execute();
    }

    public void removeStudentFromGroup(int student_id, int group_id) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call SYS.REMOVE_STUDENT_FROM_GROUP(?, ?)}");
        stmt.setInt(1, student_id);
        stmt.setInt(2, group_id);
        stmt.execute();
    }

    /* Use to find professors that you can assign to subject/group */
    public ResultSet getProfessorsOfFaculty(String faculty_id) throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery("select * from SYS.PROFESSOR_VIEW where FACULTY_ID = " + faculty_id);
    }

    public ResultSet getSubjectsOfFaculty(String faculty_id) throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery("select * from SYS.SUBJECT where FACULTY_ID = " + faculty_id);
    }

    public String getFacultyId() {
        return faculty_id;
    }
}
