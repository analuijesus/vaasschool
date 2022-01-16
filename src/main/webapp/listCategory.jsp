<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Categorias</title>
</head>
<body>

<a href="/vaasschool/criaNovaCategoria">
    <button class="categoria">Cadastre uma Nova Categoria</button>
</a>
<br/><br>

<h2>Categorias</h2>

<table>
    <thead>
    <tr>
        <th>Nome</th>
        <th>Código</th>
        <th>Ordem</th>
        <th>Descrição</th>
        <th>Desativar Categoria?</th>
        <th>Ícone</th>
        <th>Cor</th>
    </tr>
    </thead>

    <tbody>

    <c:forEach items="${category}" var="category">
        <tr>
            <td>${category.name}</td>
            <td>${category.code}</td>
            <td>${category.order}</td>
            <td>${category.description}</td>
            <c:choose>
                <c:when test="${category.active eq true}">
                    <td id="category_${category.id}">
                        <a href="#" onClick="disableNow(${category.id})">
                            Categoria Ativa! Clique para desativar
                        </a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>Inativa</td>
                </c:otherwise>
            </c:choose>
            <td><img src="${category.imagePath}"></td>
            <td>${category.colorCode}</td>

            <td><a href="/vaasschool/mostraCategoria?id=${category.id}">atualizar</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<script src="js/JQuery.js"></script>
<script src="js/disableCategory.js"></script>

</body>
</html>
