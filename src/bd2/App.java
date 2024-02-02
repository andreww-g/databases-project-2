package bd2;

import bd2.controllers.DeansWorkerController;
import bd2.controllers.LoginController;
import bd2.controllers.ProfessorController;
import bd2.controllers.StudentController;
import bd2.models.LoginModel;
import bd2.views.ProfessorView;
import bd2.views.StudentView;
import bd2.views.WorkerView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class App {
    public static Connection cn;

    public static void reconnect() {
        try {
            cn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE", "sys as sysdba", "db_password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void reconnect(LoginModel model, String pass) {
        try {
            cn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE", model.getType(), pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (model.getType().equals("STUDENT")) {
            new StudentController(new StudentView(), model);
        }
        if (model.getType().equals("PROFESSOR")) {
            new ProfessorController(new ProfessorView(), model);
        }
        if (model.getType().equals("DEANS_WORKER")) {
            new DeansWorkerController(new WorkerView(), model);
        }

    }

    public static void main(String[] args) {

        /* Set OS default look and feel */
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Start app */
        reconnect();
        new LoginController();
    }
}
