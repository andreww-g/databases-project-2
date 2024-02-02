package test;

import bd2.App;
import bd2.models.DeansWorkerModel;
import bd2.models.LoginModel;
import bd2.tools.LoginTools;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeansWorkerTest {
    private static DeansWorkerModel model;

    @BeforeAll
    static void login() throws SQLException, NoSuchAlgorithmException {
        App.reconnect();
        var loginModel = new LoginModel("DEANS_WORKER");
        var pass = LoginTools.charToSha256(new char[]{'1'});
        loginModel.login("3", pass);
        model = new DeansWorkerModel(loginModel);
        App.cn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "sys as sysdba", "db2_password");
    }

    @Test
        //Po każdym teście trzeba zmienić pesel studenta bo jest dodawany nowy student do bazy
    void testAddingStudent() {
        assertDoesNotThrow(() -> model.addStudent("Maciek", "Kowalski", "W4", "1", "99989765553"));
        assertThrows(SQLException.class, () -> model.addStudent("Maciek", "Kowalski", "W113", "1", "99887766551"));
        assertThrows(SQLException.class, () -> model.addStudent("Maciek34", "Kowalski", "W3", "1", "99887766552"));
        assertThrows(SQLException.class, () -> model.addStudent("Maciek", "Kowalski", "W4", "56", "99887766554"));
        assertThrows(SQLException.class, () -> model.addStudent("Maciek", "Kowalski", "W4", "1", "pesel"));
    }

    @Test
    void testGettingGroup() {
        assertDoesNotThrow(() -> model.getGroups());
    }

    @Test
    void testGettingStudentsFromGroup() {
        assertDoesNotThrow(() -> model.getStudentsFromGroup(5005));
        assertDoesNotThrow(() -> model.getStudentsFromGroup(5006));
    }

    @Test
    void testGettingStudents() {
        assertDoesNotThrow(() -> model.getStudents());
    }

    @Test
    void testAddingGroup() {
        assertDoesNotThrow(() -> model.addGroup(123, 5, 100002, "N", 2, "20:00", "C", 30));
        assertDoesNotThrow(() -> model.addGroup(125, 1, 100002, "N", 2, "20:00", "L", 30));
        assertThrows(SQLException.class, () -> model.addGroup(125, 1, 100002, "N", 2, "20:00", "L", 30));
        assertThrows(SQLException.class, () -> model.addGroup(123, 1, 100002, "N", 2, "20:00", "L", 30));
        assertThrows(SQLException.class, () -> model.addGroup(126, 113, 100002, "N", 2, "20:00", "L", 30));
        assertThrows(SQLException.class, () -> model.addGroup(127, 1, 15, "N", 2, "20:00", "L", 30));
        assertThrows(SQLException.class, () -> model.addGroup(128, 6, 100002, "K", 2, "20:00", "L", 30));
        assertThrows(SQLException.class, () -> model.addGroup(129, 1, 145, "N", 2, "20:00", "L", 30));
    }

    @Test
    void testEditGroup() {
        assertDoesNotThrow(() -> model.editGroup(123, 5, 100002, "N", 2, "20:00", "C", 30));
        assertDoesNotThrow(() -> model.editGroup(125, 5, 100002, "N", 3, "20:00", "L", 30));
    }

    @Test
    void testAddingStudentToGroup() {
        assertDoesNotThrow(() -> model.addStudentToGroup(200001, 2));
        assertThrows(SQLException.class, () -> model.addStudentToGroup(200001, 5321213));

    }

    @Test
    void testRemovingStudentFromGroup() {
        assertDoesNotThrow(() -> model.removeStudentFromGroup(200001, 2));
    }

    @Test
    void testDeletingStudent() {
        assertDoesNotThrow(() -> model.removeStudent(200164));
        assertDoesNotThrow(() -> model.removeStudent(200000));
    }

    @Test
    void testRemoveGroup() {
        assertDoesNotThrow(() -> model.removeGroup(123));
        assertDoesNotThrow(() -> model.removeGroup(125));
    }
}
