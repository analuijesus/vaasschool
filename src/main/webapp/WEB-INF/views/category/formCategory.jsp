<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="title" value="${categoryForm.code == null ? 'Nova Categoria' : 'Editar Categoria'}"></c:set>
<c:set var="actionCategoryUrl"
       value="${categoryForm.id == null ? '/admin/categories' : '/admin/categories/'.concat(categoryForm.code)}"></c:set>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>

    <title>${title}</title>
</head>
<body>
<div class="container">
    <form:form modelAttribute="categoryForm" action="${actionCategoryUrl}" method="post">
        <h2>${title}</h2>
        <div class="form-group">
            <label for="name">Nome</label>
            <form:input placeholder="Digite aqui o nome da categoria"
                        type="text" id="name" name="name" class="form-control" value="${categoryForm.name}"
                        path="name"/>
            <form:errors path="name" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label for="code">Código</label>
            <form:input
                    placeholder="por exemplo: desenvolvimento, mobile (não use letras maiúsculas, acentos ou caracteres especiais)"
                    type="text" id="code" name="code" class="form-control" value="${categoryForm.code}"
                    path="code"/>
            <form:errors path="code" cssClass="alert-danger"/>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" id="active" name="active" value="${categoryForm.active == false}" /> Categoria Ativa?
            </label>
        </div>
        <div class="form-group">
            <label for="order">Ordem da Categoria</label>
            <form:input placeholder="por exemplo: categoria de ordem 1 aparece antes da categoria de ordem 2"
                        type="number" id="order" name="order" min="1" class="form-control"
                        oninput="validity.valid||(value='${categoryForm.order}')" path="order"/>
            <form:errors path="order" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label for="explanatoryGuide">Guia de estudo</label>
            <form:textarea placeholder="Um texto apontando para formações para ajudar pessoas perdidas" value="${categoryForm.explanatoryGuide}"
                           id="explanatoryGuide" name="explanatoryGuide" class="form-control" rows="4" cols="60" path="explanatoryGuide"/>
        </div>
        <div class="form-group">
            <label for="imagePath">Caminho do ícone</label>
            <form:input placeholder="por exemplo: /images/categorias/programação.svg" type="text" id="imagePath" name="imagePath" class="form-control" value="${categoryForm.imagePath}" path="imagePath"/>
        </div>
        <div class="form-group">
            <label for="colorCode">Cor</label>
            <form:input placeholder="por exemplo: #fcc14a" type="color" id="colorCode" name="colorCode" class="form-control" value="${categoryForm.colorCode}" path="colorCode"/>
        </div>
        <div class="form-group">
            <label for="description">Descrição</label>
            <form:input placeholder="por exemplo: iOS, Android, PhoneGap e mais..."
                        type="textarea" id="description" name="description" class="form-control"
                        value="${categoryForm.description}" path="description"/>
        </div>
        <form:input type="hidden" name="id" value="${categoryForm.id}" path="id"/>
        <button type="submit" class="btn btn-primary">Enviar</button>

    </form:form>
</div>
</body>
</html>
