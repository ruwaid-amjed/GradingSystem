<%--
  Created by IntelliJ IDEA.
  User: ruwaid
  Date: 21/02/2024
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="course.Course, course.CourseServlet" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Student Course Registration</title>
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
        form {
            text-align: center;
        }
        select {
            padding: 10px;
            font-size: 16px;
            width: 300px;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Student Course Registration</h2>

    <table>
        <caption>Courses Registered</caption>
        <thead>
        <tr>
            <th>Course Name</th>
        </tr>
        </thead>
        <tbody>
        <%
            CourseServlet courseServlet=new CourseServlet();
            Integer stID=(Integer) request.getAttribute("stID");
            List<Course> registeredCourses=courseServlet.showCoursesForStudent(stID);

            for (Course course : registeredCourses) {
        %>
        <tr>
            <td><%= course.getName() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <form action="${pageContext.request.contextPath}/assignCourseToStudent" method="post">
        <label for="course">Select Course to Register:</label>
        <select name="course" id="course">
            <%-- Display dropdown options of available courses here --%>
            <%
                List<Course> availableCourses=courseServlet.showCoursesAvailableForStudent(stID);

                for (Course course : availableCourses) {
            %>
            <option value="<%= course.getId() %>"><%= course.getName() %></option>
            <%
                }
            %>
        </select>
        <br><br>
        <input type="hidden" name="stID" value="<%= stID %>">
        <button type="submit">Register Course</button>
    </form>
</div>
</body>
</html>
