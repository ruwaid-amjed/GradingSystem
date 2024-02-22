<%--
  Created by IntelliJ IDEA.
  User: ruwaid
  Date: 21/02/2024
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="instructor.Instructor, instructor.InstructorDAO" %>
<html>
<head>
    <title>Instructors</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
        }
        select {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Select a Student</h1>
    <form action="${pageContext.request.contextPath}/courseServlet" method="post">
        <select name="instructor">
            <%
                InstructorDAO instructorDAO = new InstructorDAO();
                List<Instructor> instructorList = instructorDAO.showAllInstructor();
                for (Instructor instructor : instructorList) {
            %>
            <option value="<%= instructor.getId() %>"><%= instructor.getName() %></option>
            <% } %>
        </select>
        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>
