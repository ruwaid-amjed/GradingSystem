package course;
import com.student.dao.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jdbc.JDBCManger;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/courseServlet")
public class CourseServlet extends HttpServlet {
    private final JDBCManger jdbcManger=new JDBCManger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String referer=req.getHeader("referer");
        try {
            jdbcManger.loadDrive();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (referer.contains("student")){
        Integer stID= Integer.parseInt(req.getParameter("student"));

        req.setAttribute("stID",stID);

        req.getRequestDispatcher("assign_course_to_student.jsp").forward(req,resp);
        }
        else if (referer.contains("instructor")) {
            Integer instructorID= Integer.parseInt(req.getParameter("instructor"));
            req.setAttribute("instructorID",instructorID);

            req.getRequestDispatcher("assign_course_to_instructor.jsp").forward(req,resp);
        }
    }

    public List<Course> showCoursesForStudent(int id) throws SQLException {
        String query = "SELECT course.courseID,course.courseName FROM course\n" +
                "INNER JOIN student_course ON course.courseID = student_course.courseID\n" +
                "INNER JOIN user ON student_course.stID = user.userID\n" +
                "WHERE user.userID = ?";
        return selectSomeCourses(query,id);
    }
    public List<Course> showCoursesAvailableForStudent(int id) throws SQLException {
        String query = "SELECT DISTINCT course.courseID,course.courseName \n" +
                "FROM course\n" +
                "LEFT JOIN student_course ON course.courseID = student_course.courseID AND student_course.stID = ?\n" +
                "WHERE student_course.courseID IS NULL\n";
        return selectSomeCourses(query,id);
    }
    public List<Course> selectSomeCourses(String query,int id) throws SQLException {
        List<Course> courses=new ArrayList<>();
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int courseID=resultSet.getInt("courseID");
            String courseName= resultSet.getString("courseName");
            Course course=new Course(courseID,courseName);
            courses.add(course);
        }
        return courses;
    }

    public List<Course> showCoursesForInstructor(int id) throws SQLException {
        String query = "select courseID,courseName from course where instructorID=?";
        return selectSomeCourses(query,id);
    }

    public List<Course> showCoursesAvailableForInstructor() throws SQLException {
        String query = "select courseID,courseName from course where instructorID is null";
        List<Course> courses=new ArrayList<>();
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int courseID=resultSet.getInt("courseID");
            String courseName= resultSet.getString("courseName");
            Course course=new Course(courseID,courseName);
            courses.add(course);
        }
        return courses;
    }

    public List<Student> showStudentInSpecificCourse(int id) throws SQLException {
        String query = "select user.userID,name from user,student_course where student_course.courseID=? and user.userID=student_course.stID;";
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<Student> studentList=new ArrayList<>();
        while (resultSet.next()) {
            int userID=resultSet.getInt("userID");
            String name= resultSet.getString("name");
            Student student=new Student(userID,name);
            studentList.add(student);

        }
        return studentList;
    }

    public List<Course> showCourses(int stID) throws SQLException {
        String query="SELECT c.courseID, c.courseName, i.name AS instructorName\n" +
                "FROM course c\n" +
                "INNER JOIN instructor i ON c.instructorID = i.instructorID\n" +
                "INNER JOIN student_course sc ON c.courseID = sc.courseID\n" +
                "INNER JOIN user u ON sc.stID = u.userID\n" +
                "WHERE u.userID = ?";
        List<Course> courses=new ArrayList<>();
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setInt(1, stID);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int courseID=resultSet.getInt("courseID");
            String courseName= resultSet.getString("courseName");
            String instructorName= resultSet.getString("instructorName");
            Course course=new Course(courseID,courseName);
            course.setInstructorName(instructorName);
            courses.add(course);
        }
        return courses;

    }
}
