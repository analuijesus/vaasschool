<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
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
                <th colspan="3">Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.name}</td>
                    <td>${category.code}</td>
                    <c:choose>
                        <c:when test="${category.active eq true}">
                            <td id="category_${category.id}">
                                <a href="#" onClick="deactivateCategory(${category.id})">
                                    Ativa
                                </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>Inativa</td>
                        </c:otherwise>
                    </c:choose>
                    <td><a href="/admin/subcategories/${category.code}"><u>Subcategorias</u></a></td>
                    <td><a href="/admin/categories/${category.code}">
                        <button type="button" class="btn btn-light" href="/admin/categories/${category.code}">Editar
                        </button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="/resources/js/jQuery.js"></script>
<script src="/resources/js/deactivate.js"></script>

</body>
</html>
