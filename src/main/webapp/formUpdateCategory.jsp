<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="${category.id == null ? 'Nova Categoria' : 'Atualizar Categoria'}"></c:set>
<c:set var="url" value="${category.id == null ? '/vaasschool/cadastraCategoria' : '/vaasschool/alteraCategoria'}"></c:set>

<c:url value="/alteraCategoria" var="updateCategoryUrl"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<form action="${url}" method="post">

    <label for="cname">Nome:</label><br>
    <input type="text" id="cname" name="name" value="${category.name}"/><br/><br>

    <label for="ccode">Código:</label><br>
    <input type="text" id="ccode" name="code" value="${category.code}"/><br/><br>

    <label for="cdescription">Descrição:</label><br>
    <input type="text" id="cdescription" name="description" value="${category.description}"/><br/><br>

    <label for="cactive">Categoria Ativa: </label><br>
    <input type="text" id="cactive" name="active" value="${category.active}"/><br/><br>

    <label for="corder">Ordem de visualização:</label><br>
    <input type="text" id="corder" name="order" value="${category.order}"/><br/><br>

    <label for="cimagePath">Link do icone:</label><br>
    <input type="text" id="cimagePath" name="imagePath" value="${category.imagePath}"/><br/><br>

    <label for="ccolorCode">Cor (Código): </label><br>
    <input type="text" id="ccolorCode" name="colorCode" value="${category.colorCode}"/><br/><br>

    <input type="hidden" name="id" value="${category.id}"/>
    <input type="submit"/>
</form>

<script src="js/formCategory.js" type="text/javascript"></script>

</body>
</html>
