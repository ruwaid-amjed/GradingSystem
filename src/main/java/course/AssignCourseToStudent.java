package course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.JDBCManger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/assignCourseToStudent")
public class AssignCourseToStudent extends HttpServlet {
    private JDBCManger jdbcManger=new JDBCManger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            jdbcManger.loadDrive();

            int stID= Integer.parseInt(req.getParameter("stID"));
            int courseID= Integer.parseInt(req.getParameter("course"));

            insertINTOStudentCourse(stID,courseID);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("admin.jsp").forward(req,resp);
    }

    public void insertINTOStudentCourse(int stID,int courseID) throws SQLException {
        String query="INSERT INTO student_course (stID,courseID) VALUES(?,?)";
        PreparedStatement statement= jdbcManger.connect().prepareStatement(query);
        statement.setInt(1,stID);
        statement.setInt(2,courseID);
        statement.executeUpdate();
    }
}
