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

    <c:forEach items="${categories}" var="categories">
        <tr>
            <td>${categories.name}</td>
            <td>${categories.code}</td>
            <td>${categories.order}</td>
            <td>${categories.description}</td>
            <c:choose>
                <c:when test="${categories.active eq true}">
                    <td id="category_${categories.id}">
                        <a href="#" onClick="disableNow(${categories.id})">
                            Categoria Ativa! Clique para desativar
                        </a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>Inativa</td>
                </c:otherwise>
            </c:choose>
            <td><img src="${categories.imagePath}"></td>
            <td>${categories.colorCode}</td>

            <td><a href="/vaasschool/mostraCategoria?id=${categories.id}">atualizar</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<script src="js/disableCategory.js"></script>

</body>
</html>
