package grades;

import com.student.dao.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jdbc.JDBCManger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/gradeServlet")
public class GradeServlet extends HttpServlet {
    private final JDBCManger jdbcManger=new JDBCManger();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            jdbcManger.loadDrive();
            HttpSession session=req.getSession();
            int courseID= (int) session.getAttribute("courseID");
            List<Student> studentList=studentListWithoutGrades(courseID);
            for (Student student:studentList){
                int grade= Integer.parseInt(req.getParameter(student.getName()));
                int stID=student.getId();
                insertIntoGrade(grade,stID,courseID);
            }


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("instructor.jsp").forward(req,resp);

    }

    public void insertIntoGrade(int grade,int stID,int courseID) throws SQLException {
        String query = "INSERT INTO grades (grade,stID,courseID) VALUES(?,?,?)";
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setInt(1, grade);
        statement.setInt(2, stID);
        statement.setInt(3, courseID);
        statement.executeUpdate();
    }
    List<Student> studentListWithGrades(int courseID) throws SQLException {
        String query="SELECT u.userID, u.name AS student_name, g.grade\n" +
                "FROM user u\n" +
                "INNER JOIN grades g ON u.userID = g.stID\n" +
                "INNER JOIN course c ON g.courseID = c.courseID\n" +
                "WHERE c.courseID = ?";
        PreparedStatement statement=jdbcManger.connect().prepareStatement(query);
        statement.setInt(1,courseID);
        ResultSet resultSet=statement.executeQuery();
        List<Student> studentList=new ArrayList<>();
        while (resultSet.next()){
            int userID= resultSet.getInt("userID");
            String name= resultSet.getString("student_name");
            Student student=new Student(userID,name);
            student.setGrade(resultSet.getInt("grade"));
            studentList.add(student);
        }
        return studentList;

    }
    List<Student>studentListWithoutGrades(int courseID) throws SQLException {
        String query="SELECT u.userID, u.name AS student_name\n" +
                "FROM user u\n" +
                "INNER JOIN student_course sc ON u.userID = sc.stID\n" +
                "INNER JOIN course c ON sc.courseID = c.courseID\n" +
                "WHERE c.courseID = ?\n" +
                "AND u.userID NOT IN (\n" +
                "    SELECT g.stID\n" +
                "    FROM grades g\n" +
                "    WHERE g.courseID = ?)" ;
        PreparedStatement statement=jdbcManger.connect().prepareStatement(query);
        statement.setInt(1,courseID);
        statement.setInt(2,courseID);
        ResultSet resultSet=statement.executeQuery();
        List<Student> studentList=new ArrayList<>();
        while (resultSet.next()){
            int userID= resultSet.getInt("userID");
            String name= resultSet.getString("student_name");
            Student student=new Student(userID,name);
            studentList.add(student);
        }
        return studentList;
    }
}
