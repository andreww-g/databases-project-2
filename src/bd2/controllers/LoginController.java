package bd2.controllers;

import bd2.App;
import bd2.models.LoginModel;
import bd2.tools.LoginTools;
import bd2.views.LoginView;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginController implements IController {
    private final LoginView view;
    private final LoginModel model;

    public LoginController() {
        this.view = new LoginView();
        this.model = new LoginModel(null);

        init();
    }

    @Override
    public void init() {
        view.getLoginButton().addActionListener(e -> login());
    }

    @Override
    public void dispose() {
        view.dispose();
    }

    private void login() {
        try {
            var login = view.getLoginField().getText();
            var pass = LoginTools.charToSha256(view.getPasswordField().getPassword());

            model.login(login, pass);
            if (model.getType() != null) {
                App.reconnect(model, "db_password");
                dispose();
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null,
                    "Wrong login and/or password. Try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
