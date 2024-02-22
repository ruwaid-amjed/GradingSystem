<%@ page import="grades.GradeService" %>
<%@ page import="java.util.List" %>
<%@ page import="grades.ShowGrades" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="jdbc.JDBCManger" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Grades</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        .gpa-label {
            text-align: right;
            margin-top: 20px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Student Grades</h2>
    <table>
        <thead>
        <tr>
            <th>Course Name</th>
            <th>Grade</th>
        </tr>
        </thead>
        <tbody>
        <%
            HttpSession session1= request.getSession();
            int stID= (int) session1.getAttribute("stID");
            GradeService service=new GradeService();
            List<ShowGrades> grades=service.showGrades(stID);
            for (ShowGrades grades1:grades){
        %>
        <tr>
            <td><%=grades1.getCourseName()%></td>
            <td><%=grades1.getGrade()%></td>
        </tr>
        <%}%>
        <div class="gpa-label">
            <label for="gpa">GPA:</label>
            <span id="gpa">
                <%
                    String sqlQuery = "SELECT u.name AS student_name, SUM(g.grade) / COUNT(DISTINCT c.courseID) AS gpa " +
                            "FROM User u " +
                            "JOIN Grades g ON u.userID = g.stID " +
                            "JOIN Course c ON g.courseID = c.courseID " +
                            "WHERE u.userID = " + stID ;
                    JDBCManger jdbcManger=new JDBCManger();
                    PreparedStatement statement=jdbcManger.connect().prepareStatement(sqlQuery);
                    ResultSet resultSet= statement.executeQuery();
                    resultSet.next();
                    double gpa =resultSet.getDouble("gpa") ; 
                    out.println(gpa);
                %>
            </span>
        </div>
        </tbody>
    </table>
</div>
</body>
</html>
