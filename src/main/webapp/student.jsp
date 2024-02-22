<%--
  Created by IntelliJ IDEA.
  User: ruwaid
  Date: 22/02/2024
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Dashboard</title>
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
        .options {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .option {
            margin: 0 10px;
        }
        .option a {
            display: block;
            padding: 20px;
            text-align: center;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .option a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Student Dashboard</h2>
    <div class="options">
        <div class="option">
            <a href="student_show_courses.jsp">Show Courses</a>
        </div>
        <div class="option">
            <a href="show_grades.jsp">Show Grades</a>
        </div>
    </div>
</div>
</body>
</html>

