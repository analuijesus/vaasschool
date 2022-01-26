<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="/resources/css/style.css" rel="stylesheet">


    <title>Categorias</title>
</head>

<body>
<div class="container">

    <h2>Categorias</h2>

    <a href="/admin/categories/new">
        <button type="button" class="btn btn-primary my-4">Nova Categoria</button>
    </a>
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Código</th>
                <th>Status</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="categories">
                <tr>
                    <td>${categories.name}</td>
                    <td>${categories.code}</td>
                    <c:choose>
                        <c:when test="${categories.active eq true}">
                            <td id="category_${categories.id}">
                                <a href="#" onClick="disableNow(${categories.id})">
                                    Ativa
                                </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>Inativa</td>
                        </c:otherwise>
                    </c:choose>
                    <td><a href="/admin/subcategories/${categories.code}"><u>Subcategorias</u></a></td>
                    <td><a href="/admin/categories/${categories.code}">
                        <button type="button" class="btn btn-light" href="/admin/categories/${categories.code}">Editar
                        </button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="/resources/js/disableCategory.js"></script>

</body>
</html>
