import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.JDBCManger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addCourse")
public class AddCourseServlet extends HttpServlet {
    private final JDBCManger jdbcManger=new JDBCManger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            jdbcManger.loadDrive();

            String courseName=req.getParameter("courseName");
            insertIntoCourse(courseName);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("admin.jsp").forward(req,resp);
    }

    public void insertIntoCourse(String courseName) throws SQLException {
        String query = "INSERT INTO course (courseName) VALUES(?)";
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setString(1, courseName);
        statement.executeUpdate();
    }
}
