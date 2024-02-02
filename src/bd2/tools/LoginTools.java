package bd2.tools;

import bd2.models.UserModel;

import javax.swing.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;

public class LoginTools {
    public static String charToSha256(char[] hash) throws NoSuchAlgorithmException {
        byte[] res = new byte[hash.length];
        for (int i = 0; i < hash.length; i++) {
            res[i] = (byte) hash[i];
        }

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(res);
        byte[] digest = md.digest();

        return String.format("%064x", new BigInteger(1, digest));
    }

    public static String randomString(int stringLength) {
        String candidates = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < stringLength) {
            int index = (int) (rnd.nextFloat() * candidates.length());
            salt.append(candidates.charAt(index));
        }
        return salt.toString();
    }

    public static String randomPassword(int stringLength) throws NoSuchAlgorithmException {
        var pass = randomString(stringLength);
        return charToSha256(pass.toCharArray());
    }

    public static String getLoginIdText(UserModel model) {
        try {
            var rs = model.getPersonalData();
            var id = model.getId();
            while (rs.next()) {
                var first_name = rs.getString("FIRST_NAME");
                var last_name = rs.getString("LAST_NAME");
                var faculty = rs.getString("FACULTY_ID");
                return String.format("%s %s (%d) / %s", first_name, last_name, id, faculty);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return null;
    }
}
