<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vaibhavjain03
  Date: 12-05-2021
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Employees</title>
    <spring:url value="/resources/css/employees.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet" />
</head>
<body>
<div class="logout">
    <span class="user">Welcome ${user} </span>
    <div class="outer">
        <div class="inner">
            <a href="/login" style="text-decoration: none"><label class="label">Log Out</label></a>
        </div>
    </div>
</div>
<a style="text-decoration: none" href="/add"><button class="btn-2">Add New Employee</button></a>
<a style="text-decoration: none" href="/download"><button class="btn-1">Download Employee Details</button></a>
<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th>Employee Code</th>
            <th>Employee Name</th>
            <th>Location</th>
            <th>Email</th>
            <th>Date of Birth</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employees}" var="employee">
        <tr>
            <td><c:out value="${employee.employeeCode}"/></td>
            <td><c:out value="${employee.name}"/></td>
            <td><c:out value="${employee.location}"/></td>
            <td><c:out value="${employee.email}"/></td>
            <td><c:out value="${employee.dateOfBirth}"/></td>
            <td><a href="/edit/${employee.employeeCode}">Edit</a>
                <a href="/delete/${employee.employeeCode}">Delete</a></td>
        </tr>
        </c:forEach>
        <tbody>
    </table>
</div>

</body>
</html>
