<%@ page import="grades.GradeServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="com.student.dao.Student" %>
<%@ page import="grades.GradeService" %>
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
        input[type="text"] {
            padding: 5px;
            width: 60px;
        }
        input[type="text"]:disabled {
            background-color: #f9f9f9;
        }
        .btn-submit {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<%
    GradeService service=new GradeService();
    HttpSession session1= request.getSession();
    int courseID= (int) session1.getAttribute("courseID");
%>
<div class="container">
    <h2>Student Grades for Course X</h2>
    <form action="${pageContext.request.contextPath}/gradeServlet" method="post">
        <table>
            <thead>
            <tr>
                <th>Student Name</th>
                <th>Grade</th>
            </tr>
            </thead>
            <tbody>
            <%
                  List<Student> studentList=service.studentListWithGrades(courseID);
                  for (Student student:studentList){
            %>
            <tr>
                <td><%= student.getName() %></td>
                <td><input type="text" value="<%= student.getGrade() %>" disabled></td>
            </tr>
            <%}%>
            <%
                studentList=service.studentListWithoutGrades(courseID);
                int i=1;
                for(Student student:studentList){
            %>
            <tr>
                <td><%=student.getName()%>></td>
                <td><input type="text" value="" name="<%=student.getName()%>"></td>
            </tr>
            <%}%>
            </tbody>
        </table>
        <button type="submit" class="btn-submit">Submit Grades</button>
    </form>
</div>
</body>
</html>
