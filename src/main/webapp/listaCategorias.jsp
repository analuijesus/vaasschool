<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Categorias</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Nome</th>
        <th>Código</th>
        <th>Ordem</th>
        <th>Descrição</th>
        <th>Status</th>
        <th>Ícone</th>
        <th>Cor</th>
    </tr>
    </thead>

    <tbody>

    <c:forEach items="${categorias}" var="category">
        <tr>
            <td>${category.name}</td>
            <td>${category.code}</td>
            <td>${category.order}</td>
            <td>${category.description}</td>
            <td>${category.active}</td>
            <td><img src=${category.imagePath}</td>
            <td>${category.colorCode}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
