<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/style.css" rel="stylesheet">

    <title>Cursos</title>

</head>
<body>
<div class="container">
    <h4>${subcategory.name}</h4>
    <h2>Cursos</h2>

    <a href="/admin/courses/new">
        <button type="button" class="btn btn-primary my-4">Novo Curso</button>
    </a>
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Código</th>
                <th colspan="2">Visibilidade</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.name}</td>
                    <td>${course.code}</td>
                    <td>${course.visibility.description}</td>
                    <td><a href="/admin/subcategories/${categoryCode}/${subcategory.code}/${course.code}">
                        <button type="button" class="btn btn-light"
                                href="/admin/courses/${categoryCode}/${subcategory.code}/${course.code}"> Editar
                        </button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <c:if test="${courses.size() > 0}">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:forEach begin="0" end="${totalPages - 1}" var="page">
                        <li class="page-item">
                            <a href="/admin/courses/${categoryCode}/${subcategory.code}?page=${page}"
                               class="page-link">${page + 1}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
            <c:if test="${courses.size() >= 5}">
                <a href="/admin/courses/${categoryCode}/${subcategory.code}?page=${page.getNumber() - 1}"
                   class="page-link">Anterior</a>
                <a href="/admin/courses/${categoryCode}/${subcategory.code}?page=${page.getNumber() + 1}"
                   class="page-link">Próximo</a>
            </c:if>
        </c:if>
    </div>
</div>
</body>
</html>