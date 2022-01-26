<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="title" value="${categoryForm.code == null ? 'Nova Categoria' : 'Editar Categoria'}"></c:set>
<c:set var="actionCategoryUrl" value="${categoryForm.id == null ? '/admin/categories' : '/admin/categories/'.concat(categoryForm.code)}"></c:set>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/bootstrap/css/bootstrap.css" rel="stylesheet"/>

    <title>${title}</title>
</head>
<body>
<div class="container">
    <form:form modelAttribute="categoryForm" action="${actionCategoryUrl}" method="post">
        <h2>${title}</h2>
        <div class="form-group">
            <label for="name">Nome</label><br>
            <form:input placeholder="Digite aqui o nome da categoria"
                        type="text" id="name" class="form-control" name="name" value="${categoryForm.name}"
                        path="name"/>
            <form:errors path="name" cssClass="alert-danger"/><br>


            <label for="code">Código</label><br>
            <form:input
                    placeholder="por exemplo: desenvolvimento, mobile (não use letras maiúsculas, acentos ou caracteres especiais)"
                    type="text" id="code" class="form-control" name="code" value="${categoryForm.code}"
                    path="code"/>
            <form:errors path="code" cssClass="alert-danger"/><br>

            <input type="checkbox" id="active" name="active"
                   value="${categoryForm.active == false}"/> <label>Categoria
            Ativa?</label> Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc<br><br>

            <label for="order">Ordem da Categoria</label><br>
            <form:input placeholder="por exemplo: categoria de ordem 1 aparece antes da categoria de ordem 2"
                        type="number" id="order" class="form-control" name="order" min="1"
                        oninput="validity.valid||(value='${categoryForm.order}')" path="order"/>
            <form:errors path="order" cssClass="alert-danger"/><br>

            <label for="explanatoryGuide">Guia de estudo</label>
            <form:textarea placeholder="Um texto apontando para formações para ajudar pessoas perdidas"
                           value="${categoryForm.explanatoryGuide}" id="explanatoryGuide" class="form-control"
                           name="explanatoryGuide" rows="4"
                           cols="60" path="explanatoryGuide"/><br>

            <label for="imagePath">Caminho do ícone</label><br>
            <form:input placeholder="por exemplo: /images/categorias/programação.svg"
                        type="text" id="imagePath" class="form-control" name="imagePath"
                        value="${categoryForm.imagePath}" path="imagePath"/><br>

            <label for="colorCode">Cor</label><br>
            <form:input placeholder="por exemplo: #fcc14a"
                        type="text" id="colorCode" class="form-control" name="colorCode"
                        value="${categoryForm.colorCode}" path="colorCode"/><br>

            <label for="description">Descrição</label><br>
            <form:input placeholder="por exemplo: iOS, Android, PhoneGap e mais..."
                        type="textarea" id="description" class="form-control" name="description"
                        value="${categoryForm.description}" path="description"/><br>

            <form:input type="hidden" name="id" value="${categoryForm.id}" path="id"/>

            <button type="submit" class="btn btn-primary">Enviar</button>
        </div>
    </form:form>
</div>
</body>
</html>
