package test;

import bd2.App;
import bd2.models.LoginModel;
import bd2.tools.LoginTools;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private static LoginModel model;

    @BeforeAll
    static void login() {
        App.reconnect();
        model = new LoginModel(null);
    }

    @Test
    void testLoginAsStudent() throws NoSuchAlgorithmException, SQLException {
        var pass = LoginTools.charToSha256(new char[]{'1'});
        model.login("1", pass);
    }

    @Test
    void testLoginWrongData() {
        assertThrows(SQLException.class, () -> model.login("0", "0"));
    }

    @Test
    void testSha256Conversion() throws NoSuchAlgorithmException {
        var string_test = "test".toCharArray();
        var string_test_sha = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";
        assertEquals(string_test_sha, LoginTools.charToSha256(string_test));

        var string_lorem = "lorem".toCharArray();
        var string_lorem_sha = "3400bb495c3f8c4c3483a44c6bc1a92e9d94406db75a6f27dbccc11c76450d8a";
        assertEquals(string_lorem_sha, LoginTools.charToSha256(string_lorem));

        assertNotEquals(string_test_sha, LoginTools.charToSha256(string_lorem));
    }

    @Test
    void testFindId() throws NoSuchAlgorithmException, SQLException {
        var pass = LoginTools.charToSha256(new char[]{'1'});
        model.login("1", pass);

        assertEquals(200001, model.getId());
    }
}
