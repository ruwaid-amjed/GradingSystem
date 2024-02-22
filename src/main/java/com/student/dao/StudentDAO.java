package com.student.dao;
import jdbc.JDBCManger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final JDBCManger jdbcManger=new JDBCManger();

    public StudentDAO() throws ClassNotFoundException {
        jdbcManger.loadDrive();
    }


    public List<Student> getAllStudent() throws SQLException {
        String query="select userID,name from user";
        List<Student> students=new ArrayList<>();
        PreparedStatement statement=jdbcManger.connect().prepareStatement(query);
        ResultSet resultSet= statement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("userID");
            String name=resultSet.getString("name");
            Student student=new Student(id,name);
            students.add(student);
        }
        return students;
    }
}
