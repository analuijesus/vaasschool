<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Dashboard</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/></head>
<body>
<div class="container">
    <h1>Dashboard</h1>
    <div class="table-responsive">
        <h2>Categoria por Cursos</h2>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Nome Categoria</th>
                <th>Quantidade de cursos</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allCategories}" var="category">
                <tr>
                    <td>${category.name} </td>
                    <td>${category.numberOfCourses}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <h2>Instrutor com mais cursos</h2>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Nome Instrutor</th>
                <th>Quantidade Cursos</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${instructorsWithMoreCourses}" var="course">
                <tr>
                    <td>${course.instructorName} </td>
                    <td>${course.numberOfCourses}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>