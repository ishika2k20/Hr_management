<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <spring:url value="/resources/css/login.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet" />
</head>
<body>
    <form class="login" method="post">
        <span style="color: red">${errorMessage}</span>
        <input type="text" name="userName" placeholder="Username">
        <input type="password" name="password" placeholder="Password">
        <button formmethod="POST" formaction="/login" type="submit">Login</button>
    </form>
</body>
</html>