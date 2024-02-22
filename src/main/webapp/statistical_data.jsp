<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="course.Course, course.CourseServlet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="grades.StatisticalData" %>
<%@ page import="grades.StatisticalDataDAO" %>
<html>
<head>
    <title>Statistical Data</title>
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
    </style>
</head>
<body>
<div class="container">
    <h2>Statistical Data for Instructor</h2>
    <table>
        <thead>
        <tr>
            <th>Course Name</th>
            <th>Max Grade</th>
            <th>Min Grade</th>
            <th>Average Grade</th>
        </tr>
        </thead>
        <tbody>
        <%
            StatisticalDataDAO dataDAO=new StatisticalDataDAO();
            List<StatisticalData> dataList=dataDAO.statisticalDataList();
            for (StatisticalData data : dataList) {
        %>
        <tr>
            <td><%= data.getCourseName() %></td>
            <td><%= data.getMax() %></td>
            <td><%= data.getMin() %></td>
            <td><%= data.getAvg() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
