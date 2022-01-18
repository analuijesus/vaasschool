<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="${category.id == null ? 'Nova Categoria' : 'Atualizar Categoria'}"></c:set>
<c:set var="actionCategoryUrl" value="${category.id == null ? '/vaasschool/cadastraCategoria' : '/vaasschool/alteraCategoria'}"></c:set>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<form action="${actionCategoryUrl}" method="post">

    <label for="name">Nome:</label><br>
    <input type="text" id="name" name="name" value="${category.name}"/><br><br>

    <label for="code">Código:</label><br>
    <input type="text" id="code" name="code" value="${category.code}"/><br><br>

    <label for="description">Descrição:</label><br>
    <input type="textarea" id="description" name="description" value="${category.description}"/><br><br>

    <label>Categoria Ativa? </label><br><br>
    <input type="radio" name="active" value="true"> Sim
    <input type="radio" name="active" value="false"> Não <br/><br>

    <label for="order">Ordem de visualização:</label><br>
    <input type="number" id="order" name="order"  min="1" oninput="validity.valid||(value='${category.order}')"><br/><br>

    <label for="imagePath">Link do icone:</label><br>
    <input type="text" id="imagePath" name="imagePath" value="${category.imagePath}"/><br><br>

    <label for="colorCode">Cor (Código): </label><br>
    <input type="text" id="colorCode" name="colorCode" value="${category.colorCode}"/><br><br>

    <input type="hidden" name="id" value="${category.id}"/>
    <input type="submit"/>
</form>

</body>
</html>
