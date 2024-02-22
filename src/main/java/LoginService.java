import jdbc.JDBCManger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    private final JDBCManger jdbcManger=new JDBCManger();

    public  String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());

        // Convert byte array to hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public boolean authenticateUser(String username, String password) throws SQLException {
        String query = "select * from login where userName=? and password=?";
        try (PreparedStatement statement = jdbcManger.connect().prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    public String getRole(String username, String password) throws SQLException {
        String query = "SELECT role FROM login WHERE username = ? AND password = ?";
        ResultSet resultSet = jdbcManger.logInResult(username, password, query);
        resultSet.next();
        return resultSet.getString("role");
    }

    public Integer getID(String username, String password) throws SQLException {
        String query = "SELECT id FROM login WHERE username = ? AND password = ?";
        ResultSet resultSet = jdbcManger.logInResult(username, password, query);
        resultSet.next();
        return resultSet.getInt("id");
    }

}
