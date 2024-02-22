<%@ page import="course.CourseServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="course.Course" %><%--
  Created by IntelliJ IDEA.
  User: ruwaid
  Date: 22/02/2024
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose Course</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 60%;
            margin: 100px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
        }
        select {
            padding: 10px;
            font-size: 16px;
            width: 100%;
            box-sizing: border-box;
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
    <h2>Choose Course</h2>
    <form action="${pageContext.request.contextPath}/courseSelectionServlet" method="post">
        <select name="course" id="course">
            <%
                HttpSession session1= request.getSession();
                int instructorID= (int) session1.getAttribute("instructorID");
                CourseServlet servlet=new CourseServlet();
                List<Course> courseList=servlet.showCoursesForInstructor(instructorID);
                for (Course course : courseList){
            %>
            <option value="<%= course.getId() %>"><%= course.getName() %></option>
            <%}%>
        </select>
        <br>
        <button type="submit">Select Course</button>
    </form>
</div>
</body>
</html>

