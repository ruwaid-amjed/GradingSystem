import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jdbc.JDBCManger;

import java.io.IOException;

@WebServlet(urlPatterns = "/courseSelectionServlet")
public class CourseSelectionServlet extends HttpServlet {

    private final JDBCManger jdbcManger=new JDBCManger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            jdbcManger.loadDrive();

            int courseID= Integer.parseInt(req.getParameter("course"));

            HttpSession session=req.getSession();
            session.setAttribute("courseID",courseID);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("input_grades.jsp").forward(req,resp);
    }
}
