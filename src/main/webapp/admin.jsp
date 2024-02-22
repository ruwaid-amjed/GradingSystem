<%--
  Created by IntelliJ IDEA.
  User: ruwaid
  Date: 20/02/2024
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        .menu-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .menu {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        .menu li {
            display: inline-block;
            margin-right: 20px;
        }

        .menu li:last-child {
            margin-right: 0;
        }

        .menu a {
            text-decoration: none;
            color: #333;
            padding: 10px 20px;
            border-radius: 5px;
            background-color: #f0f0f0;
            transition: background-color 0.3s;
        }

        .menu a:hover {
            background-color: #ddd;
        }
    </style>
</head>
<body>

<div class="menu-container">
    <ul class="menu">
        <li><a href="add_student.jsp">Add a Student</a></li>
        <li><a href="add_instructor.jsp">Add an Instructor</a></li>
        <li><a href="add_course.jsp">Add a Course</a></li>
        <li><a href="select_student.jsp">Assign Course to Student</a></li>
        <li><a href="select_instructor.jsp">Assign Course to Instructor</a></li>
        <li><a href="show_all_classes.jsp">Show All Classes</a></li>
    </ul>
</div>

</body>
</html>
