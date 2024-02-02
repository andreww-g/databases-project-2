package bd2.models.dialogs;

import bd2.App;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NewGroupModel {
    public int group_id;
    public int professor_id;
    public int subject_id;
    public String parity;
    public int day;
    public String time;
    public String form;
    public int student_limit;

    public static ResultSet getSubjectsOfFaculty(String faculty_id) throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery("select * from SYS.SUBJECT where FACULTY_ID = '" + faculty_id + "'");
    }

    public static String findSubjectNameById(int subject_id) throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        var rs = st.executeQuery("select * from SYS.SUBJECT where SUBJECT_ID = " + subject_id);
        if (rs.next()) {
            return rs.getString("SUBJECT_NAME");
        }
        return null;
    }
}
