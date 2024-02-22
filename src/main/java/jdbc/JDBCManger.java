package jdbc;

import java.sql.*;

public class JDBCManger {
    public void loadDrive() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/gradesystem", "Ruwaid", "Pirate65");
    }

    public ResultSet logInResult(String username, String password, String query) throws SQLException {
        PreparedStatement statement = connect().prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        return statement.executeQuery();
    }
    public void insertINTO(String query,String name,int loginID) throws SQLException {
        PreparedStatement statement = connect().prepareStatement(query);
        statement.setString(1, name);
        statement.setInt(2, loginID);
        statement.executeUpdate();
    }
}
