package bd2.models;

import bd2.App;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class LoginModel {
    protected int id;
    protected String type;

    public LoginModel(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public LoginModel(String type) {
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public void login(String login, String pass) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{? = call SYS.CHECK_LOGIN(?, ?)}");
        stmt.registerOutParameter(1, Types.VARCHAR);
        stmt.setString(2, login);
        stmt.setString(3, pass);
        stmt.execute();
        this.type = stmt.getString(1);

        if (type != null && !type.equals("")) {
            this.id = findId(login, pass);
        }
    }

    private int findId(String login, String pass) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{? = call SYS.GET_ID(?, ?)}");
        stmt.registerOutParameter(1, Types.NUMERIC);
        stmt.setString(2, login);
        stmt.setString(3, pass);
        stmt.execute();

        return stmt.getInt(1);
    }
}
