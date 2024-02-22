import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.JDBCManger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addInstructor")
public class AddInstructorServlet extends HttpServlet {
    private final JDBCManger jdbcManger=new JDBCManger();
    private final LoginService service=new LoginService();
    private final AddStudentServlet studentService=new AddStudentServlet();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            jdbcManger.loadDrive();

            String userName=req.getParameter("username");
            String password=service.hashPassword(req.getParameter("password"));

            insertIntoLogin(userName,password);

            int loginID=studentService.getID(userName,password);
            String instructorName=req.getParameter("instructorName");

            insertIntoInstructor(instructorName,loginID);

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
        statement.setString(3, "instructor");
        statement.executeUpdate();
    }

    public void insertIntoInstructor(String name, int loginID) throws SQLException {
        String query = "INSERT INTO instructor (name,loginID) VALUES(? , ?)";
        jdbcManger.insertINTO(query,name,loginID);
    }
}
