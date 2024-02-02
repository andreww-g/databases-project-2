
package test;

import bd2.App;
import bd2.models.LoginModel;
import bd2.models.StudentModel;
import bd2.tools.LoginTools;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    private static StudentModel model;

    @BeforeAll
    static void login() throws SQLException, NoSuchAlgorithmException {
        App.reconnect();
        var loginModel = new LoginModel("STUDENT");
        var pass = LoginTools.charToSha256(new char[]{'1'});
        loginModel.login("1", pass);
        model = new StudentModel(loginModel);
        App.cn = DriverManager.getConnection(
                "jdbc:oracle:thin:@146.59.17.101:1521:XE", "STUDENT", "pass");
    }

    @Test
    void testPersonalData() throws SQLException {
        var rs = model.getPersonalData();

        var id = 200001;
        var first_name = "test_first_name";
        var last_name = "test_last_name";
        var faculty = "Wydział 3";

        int rs_id = 0;
        String rs_first_name = "", rs_last_name = "", rs_faculty = "";

        if (rs.next()) {
            rs_id = rs.getInt("STUDENT_ID");
            rs_first_name = rs.getString("FIRST_NAME");
            rs_last_name = rs.getString("LAST_NAME");
            rs_faculty = rs.getString("FACULTY");
        }

        assertEquals(id, rs_id);
        assertEquals(first_name, rs_first_name);
        assertEquals(last_name, rs_last_name);
        assertEquals(faculty, rs_faculty);
    }

    @Test
    void testAvgGrade() throws SQLException {
        double rs_avg_grade = 0f;

        var rs = model.getPersonalData();

        if (rs.next())
            rs_avg_grade = rs.getDouble("AVG_GRADE");

        ArrayList<Float> grades = new ArrayList<>();
        rs = model.getGrades();
        while (rs.next()) {
            var grade = rs.getFloat("GRADE");
            if (grade != 0f)
                grades.add(grade);
        }

        float sum = 0f;
        for (Float grade : grades) sum += grade;

        double avg_grade = sum / grades.size();
        BigDecimal bd = new BigDecimal(avg_grade).setScale(2, RoundingMode.HALF_UP);
        avg_grade = bd.doubleValue();

        assertEquals(rs_avg_grade, avg_grade);
    }
}

