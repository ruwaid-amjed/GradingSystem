<%--
  Created by IntelliJ IDEA.
  User: ruwaid
  Date: 21/02/2024
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Instructor</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        .container {
            width: 50%;
            margin: 50px auto;
        }

        h2 {
            text-align: center;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>
<div class="container">
    <h2>Add Instructor</h2>
    <form action="${pageContext.request.contextPath}/addInstructor" method="post">
        <div class="form-group">
            <label for="username">Create a Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Create a Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="instructorName">Enter the Instructor Name:</label>
            <input type="text" id="instructorName" name="instructorName" required>
        </div>
        <div class="form-group">
            <button type="submit">Add Instructor</button>
        </div>
    </form>
</div>
</body>
</html>
