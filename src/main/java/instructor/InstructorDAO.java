package instructor;

import jdbc.JDBCManger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {
    private JDBCManger jdbcManger=new JDBCManger();

    public InstructorDAO() throws ClassNotFoundException {
        jdbcManger.loadDrive();
    }

    public List<Instructor> showAllInstructor() throws SQLException {
        String query="select instructorID,name from instructor";
        PreparedStatement statement=jdbcManger.connect().prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Instructor> instructorList=new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("instructorID");
            String name= resultSet.getString("name");
            Instructor instructor=new Instructor(id,name);
            instructorList.add(instructor);
        }
        return instructorList;
    }
}
