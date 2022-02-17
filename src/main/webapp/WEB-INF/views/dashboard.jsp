<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/style.css" rel="stylesheet">

    <title>Dashboard</title>
</head>
<body>
<div class="container">
    <h1>Dashboard</h1>

    <a href="/admin/categories">
        <button type="button" class="btn btn-primary my-4">Listar Categorias</button>
    </a>

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
            <tr>
                <td>${instructorsWithMoreCourses.instructorName} </td>
                <td>${instructorsWithMoreCourses.numberOfCourses}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>