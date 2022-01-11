<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <script type="text/javascript" src="js/jquery.js"></script>
    <title>Categorias</title>
</head>
<body>
<script type="text/javascript">
    function disableNow(id) {
        $.post("desativaCategoria", {'id': id}, function () {
            $("#categoria_" + id).html("Categoria Desativada");
        });
    }
</script>

<a href="/vaasschool/formCreateCategory.jsp">
    <button class="categoria">Cadastre uma Nova Categoria</button>
</a>
<br/><br>

<h2>Categorias Cadastradas</h2>

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

    <c:forEach items="${categorias}" var="category">
        <tr>
            <td>${category.name}</td>
            <td>${category.code}</td>
            <td>${category.order}</td>
            <td>${category.description}</td>
            <c:choose>
                <c:when test="${category.active eq true}">
                <td id="categoria_${category.id}">
                    <a href="#" onClick="disableNow(${category.id})">
                        Categoria Ativa! Clique para desativar
                    </a>
                </td>
                </c:when>
                <c:otherwise>
                    <td>Categoria Inativa!</td>
                </c:otherwise>
            </c:choose>
            <td><img src=${category.imagePath}</td>
            <td>${category.colorCode}</td>

            <td><a href="/vaasschool/mostraCategoria?id=${category.id}">atualizar</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
