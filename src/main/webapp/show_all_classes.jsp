<%--
  Created by IntelliJ IDEA.
  User: ruwaid
  Date: 21/02/2024
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.ResultSetMetaData" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course Enrollment Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Course Enrollment Information</h2>

    <table>
        <caption>Courses, Instructors, and Enrolled Students</caption>
        <thead>
        <tr>
            <th>Course Name</th>
            <th>Instructor</th>
            <th>Enrolled Students</th>
        </tr>
        </thead>
        <tbody>
        <%
            String sqlQuery = "SELECT courseName, instructor.name, GROUP_CONCAT(user.name) AS studentNames " +
                    "FROM course, instructor, user, student_course " +
                    "WHERE instructor.instructorID = course.instructorID " +
                    "AND student_course.stID = user.userID " +
                    "AND student_course.courseID = course.courseID " +
                    "GROUP BY courseName, instructor.name";

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/gradesystem", "Ruwaid", "Pirate65");
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sqlQuery)) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (resultSet.next()) {
        %>
        <tr>
            <% for (int i = 1; i <= columnCount; i++) { %>
            <td><%= resultSet.getString(i) %></td>
            <% } %>
        </tr>
        <% } %>
        <% } catch (SQLException e) {
            e.printStackTrace();
        } %>
        </tbody>
    </table>
</div>
</body>
</html>

