
package test;

import bd2.App;
import bd2.models.LoginModel;
import bd2.models.ProfessorModel;
import bd2.tools.LoginTools;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ProfessorTest {
    private static ProfessorModel model;

    @BeforeAll
    static void login() throws SQLException, NoSuchAlgorithmException {
        App.reconnect();
        var loginModel = new LoginModel("PROFESSOR");
        var pass = LoginTools.charToSha256(new char[]{'1'});
        loginModel.login("2", pass);
        model = new ProfessorModel(loginModel);
        App.cn = DriverManager.getConnection(
                "jdbc:oracle:thin:@146.59.17.101:1521:XE", "PROFESSOR", "pass");
    }

    @Test
    void testSettingGrade() {
        assertDoesNotThrow(() -> model.addGrade(200001, 2, 4.5));
        assertThrows(SQLException.class, () -> model.removeGrade(200001, -1));
        assertDoesNotThrow(() -> model.removeGrade(200001, 2));
    }

    @Test
    void testGetGroups() {
        assertDoesNotThrow(() -> model.getGroups());
    }

    @Test
    void testGetStudentsFromGroup() throws SQLException {
        assertDoesNotThrow(() -> model.getStudents(2));

        var rs = model.getStudents(-1);
        assertFalse(rs.next());
    }
}

