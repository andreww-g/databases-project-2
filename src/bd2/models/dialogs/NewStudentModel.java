package bd2.models.dialogs;

import bd2.App;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NewStudentModel {
    public String firstName;
    public String lastName;
    public String facultyID;
    public String specialization_id;
    public String pesel;

    public static ResultSet getSpecsOfFaculty(String faculty_id) throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery("select * from SYS.SPECIALIZATION where FACULTY_ID = '" + faculty_id + "'");
    }

    public String findSpecIdByName(String spec_name, String faculty_id) throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        var rs = st.executeQuery("select * from SYS.SPECIALIZATION where NAME = '" + spec_name + "' and faculty_id = '" + faculty_id + "'");
        if (rs.next()) {
            return rs.getString("SPECIALIZATION_ID");
        }
        return null;
    }
}
