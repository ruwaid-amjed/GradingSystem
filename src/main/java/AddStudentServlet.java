import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.JDBCManger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addStudent")
public class AddStudentServlet extends HttpServlet {
    private final JDBCManger jdbcManger=new JDBCManger();
    private final LoginService service=new LoginService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            jdbcManger.loadDrive();

            String userName=req.getParameter("username");
            String password=service.hashPassword(req.getParameter("password"));

            insertIntoLogin(userName,password);

            int loginID= getID(userName,password);
            String stName=req.getParameter("studentName");
            insertIntoStudent(stName,loginID);
        } catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("admin.jsp").forward(req,resp);

    }


    public void insertIntoLogin(String userName, String password) throws SQLException {
        String query = "INSERT INTO login (userName,password,role) VALUES(? , ? , ?)";
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setString(1, userName);
        statement.setString(2, password);
        statement.setString(3, "student");
        statement.executeUpdate();
    }

    public void insertIntoStudent(String name, int loginID) throws SQLException {
        String query = "INSERT INTO user (name,loginID) VALUES(? , ?)";
        jdbcManger.insertINTO(query,name,loginID);
    }

    public Integer getID(String username, String password) throws SQLException {
        String query = "SELECT id FROM login WHERE username = ? AND password = ?";
        ResultSet resultSet = logInResult(username, password, query);
        resultSet.next();
        return resultSet.getInt("id");
    }

    public ResultSet logInResult(String username, String password, String query) throws SQLException {
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        return statement.executeQuery();
    }
}
