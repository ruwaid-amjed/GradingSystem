<%--
  Created by IntelliJ IDEA.
  User: ruwaid
  Date: 20/02/2024
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        form div {
            margin-bottom: 15px;
        }
        form div input[type="text"],
        form div input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        form div input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        form div input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .error-message {
            color: red;
            margin-top: 10px;
            font-size: 14px;
        }
    </style>
</head>
<body>

<form action="${pageContext.request.contextPath}/login.do" method="post">
    <div>
        <label for="name">Name:</label>
        <input name="name" type="text" id="name">
    </div>
    <div>
        <label for="password">Password:</label>
        <input name="password" type="password" id="password">
    </div>
    <div>
        <input type="submit" value="Submit">
    </div>
    <div class="error-message">
            ${errorMessage}
    </div>
</form>

</body>
</html>

