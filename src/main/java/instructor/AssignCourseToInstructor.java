package instructor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.JDBCManger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/assignCourseToInstructor")
public class AssignCourseToInstructor extends HttpServlet {
    private JDBCManger jdbcManger=new JDBCManger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            jdbcManger.loadDrive();

            int courseID= Integer.parseInt(req.getParameter("course"));
            int instructorID= Integer.parseInt(req.getParameter("instructorID"));

            assignCourseToInstructor(courseID,instructorID);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("admin.jsp").forward(req,resp);
    }

    public void assignCourseToInstructor(int courseID,int instructorID) throws SQLException {
        String query="update course set instructorID=? where courseID=?";
        PreparedStatement statement= jdbcManger.connect().prepareStatement(query);
        statement.setInt(1,instructorID);
        statement.setInt(2,courseID);
        statement.executeUpdate();
    }
}
