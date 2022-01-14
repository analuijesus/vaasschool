<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/cadastraCategoria" var="createCategoryUrl"/>
<html>
<head>
    <title>TESTE</title>
</head>
<body>
<form action="${createCategoryUrl}" method="post">

    <input type="button" value="Carrega formulário" onclick="displayForm()"/>

</form>
<script src="js/formCategory.js" type="text/javascript"></script>
</body>
</html>
