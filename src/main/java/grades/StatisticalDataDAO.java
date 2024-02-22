package grades;

import jdbc.JDBCManger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticalDataDAO {
    private JDBCManger jdbcManger=new JDBCManger();
    public List<StatisticalData> statisticalDataList() throws SQLException {
        String query="SELECT \n" +
                "    c.courseName AS CourseName,\n" +
                "    MAX(g.grade) AS MaxGrade,\n" +
                "    MIN(g.grade) AS MinGrade,\n" +
                "    AVG(g.grade) AS AvgGrade\n" +
                "FROM \n" +
                "    Course c\n" +
                "INNER JOIN \n" +
                "    Grades g ON c.courseID = g.courseID\n" +
                "GROUP BY c.courseName";
        PreparedStatement statement=jdbcManger.connect().prepareStatement(query);
        ResultSet resultSet= statement.executeQuery();
        List<StatisticalData> statisticalDataList=new ArrayList<>();
        while (resultSet.next()){
            String courseName= resultSet.getString("CourseName");
            int max= resultSet.getInt("MaxGrade");
            int min=resultSet.getInt("MinGrade");
            int avg= resultSet.getInt("AvgGrade");
            StatisticalData statisticalData=new StatisticalData(courseName,max,min,avg);
            statisticalDataList.add(statisticalData);
        }
        return statisticalDataList;
    }
}
