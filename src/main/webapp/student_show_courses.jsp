<%--
  Created by IntelliJ IDEA.
  User: ruwaid
  Date: 22/02/2024
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="course.CourseServlet" %>
<%@ page import="course.Course" %>

<%
  HttpSession session1= request.getSession();
  int stID= (int) session1.getAttribute("stID");

%>

<!DOCTYPE html>
<html>
<head>
  <title>Student Courses</title>
  <style>
    table {
      border-collapse: collapse;
      width: 100%;
    }
    th, td {
      border: 1px solid black;
      padding: 8px;
      text-align: left;
    }
    th {
      background-color: #f2f2f2;
    }
  </style>
</head>
<body>
<h2>Courses Registered</h2>
<table>
  <tr>
    <th>Course Name</th>
    <th>Instructor</th>
  </tr>
  <%
    CourseServlet courseServlet=new CourseServlet();
    List<Course> courseList=courseServlet.showCourses(stID);
    for (Course course:courseList){
  %>
  <tr>
    <td><%= course.getName() %></td>
    <td><%= course.getInstructorName() %></td>
  </tr>
  <% } %>
</table>
</body>
</html>

