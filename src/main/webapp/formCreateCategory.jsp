<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/cadastraCategoria" var="createCategory"/>
<html>
<head>
    <title>Nova Categoria</title>
</head>
<body>
<form action="${createCategory}" method="post">

    <label for="cname">Nome:</label><br>
    <input type="text" id="cname" name="name" value="${category.name}"/><br/><br>

    <label for="ccode">Código:</label><br>
    <input type="text" id="ccode" name="code" value="${category.code}"/><br/><br>

    <label for="cdescription">Descrição:</label><br>
    <input type="text" id="cdescription" name="description" value="${category.description}"/><br/><br>

    <label for="cexplanatoryGuide">Guia:</label><br>
    <input type="text" id="cexplanatoryGuide" name="explanatoryGuide" value="${category.explanatoryGuide}"/><br/><br>

    <label for="cactive">Categoria Ativa: </label><br>
    <input type="text" id="cactive" name="active" value="${category.active}"/><br/><br>

    <label for="corder">Ordem de visualização:</label><br>
    <input type="text" id="corder" name="order" value="${category.order}"/><br/><br>

    <label for="cimagePath">Link do icone:</label><br>
    <input type="text" id="cimagePath" name="imagePath" value="${category.imagePath}"/><br/><br>

    <label for="ccolorCode">Cor (Código): </label><br>
    <input type="text" id="ccolorCode" name="colorCode" value="${category.colorCode}"/><br/><br>

    <input type="submit" name="Nova Categoria"/>
</form>

<script type="text/javascript">
    function finalizaAgora(active) {
        $.get("finalizaTarefa?status=" + active);
    }
</script>

</body>
</html>
