<%@ page import="instructor.Instructor" %>
<%@ page import="java.util.List" %>
<%@ page import="course.Course" %>
<%@ page import="course.CourseServlet" %>
<%@ page import="com.student.dao.Student" %><%--
  Created by IntelliJ IDEA.
  User: ruwaid
  Date: 22/02/2024
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Instructor Dashboard</title>
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
        .course {
            margin-bottom: 20px;
        }
        .course-header {
            background-color: #007bff;
            color: #fff;
            padding: 10px;
            border-radius: 5px 5px 0 0;
        }
        .course-body {
            padding: 10px;
            border: 1px solid #ddd;
            border-top: none;
            border-radius: 0 0 5px 5px;
        }
        .students-list {
            list-style-type: none;
            padding: 0;
        }
        .student {
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Instructor Dashboard</h2>
    <%
        HttpSession session1= request.getSession();
        int instructorID= (int) session1.getAttribute("instructorID");
        CourseServlet courseServlet=new CourseServlet();
        List<Course> courses = courseServlet.showCoursesForInstructor(instructorID);
        for (Course course : courses) {
    %>
    <div class="course">
        <div class="course-header">
            <%= course.getName() %>
        </div>
        <div class="course-body">
            <ul class="students-list">
                <%
                    List<Student> students = courseServlet.showStudentInSpecificCourse(course.getId());
                    for (Student student : students) {
                %>
                <li class="student"><%= student.getName() %></li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
    <%
        }
    %>
</div>
</body>
</html>

