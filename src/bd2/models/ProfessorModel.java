package bd2.models;

import bd2.App;

import java.sql.*;

public class ProfessorModel extends UserModel {
    private ResultSet groups;
    private ResultSet groupsStudent;

    public ProfessorModel(LoginModel loginModel) {
        super(loginModel.getId(), loginModel.getType());
    }

    public void removeGrade(int student_id, int group_id) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call SYS.REMOVE_GRADE(?, ?)}");
        stmt.setInt(1, student_id);
        stmt.setInt(2, group_id);
        stmt.execute();
    }

    /* Can also use to edit grade */
    public void addGrade(int student_id, int group_id, Double grade) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call SYS.ADD_GRADE(?, ?, ?)}");
        stmt.setInt(1, student_id);
        stmt.setInt(2, group_id);
        if (grade == null)
            stmt.setNull(3, Types.NULL);
        else
            stmt.setDouble(3, grade);
        stmt.execute();
    }

    public ResultSet getStudents(int group_id) throws SQLException {
        fetchStudents(group_id);
        return groupsStudent;
    }

    private void fetchStudents(int group_id) throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        groupsStudent = st.executeQuery("select * from SYS.GROUP_STUDENTS_VIEW where professor_id = " + this.id + "and group_id = " + group_id);
    }


    public ResultSet getStudentsGrades() throws SQLException {
        fetchStudentsGrades();
        return groupsStudent;
    }

    private void fetchStudentsGrades() throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        groupsStudent = st.executeQuery("select * from SYS.GROUP_STUDENTS_VIEW where professor_id = " + this.id);
    }

    public ResultSet getGroups() throws SQLException {
        fetchGroups();
        return groups;
    }

    private void fetchGroups() throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        groups = st.executeQuery("select * from SYS.GROUP_VIEW where professor_id = " + this.id);
    }
}