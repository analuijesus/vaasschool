<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="title" value="${subcategoryForm.code == null ? 'Nova Subcategoria' : 'Editar Subcategoria'}"></c:set>
<c:set var="actionSubcategoryUrl"
       value="${subcategoryForm.id == null ? '/admin/subcategories' : '/admin/subcategories/'.concat(category.code).concat('/').concat(subcategoryForm.code)}"></c:set>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>

    <title>${title}</title>
</head>
<body>
<div class="container">
    <form:form modelAttribute="subcategoryForm" action="${actionSubcategoryUrl}" method="post">
        <h2>${title}</h2>
        <div class="form-group">
            <label for="name">Nome</label>
            <form:input placeholder="Digite aqui o nome da subcategoria"
                        type="text" id="name" class="form-control" name="name" value="${subcategoryForm.name}"
                        path="name"/>
            <form:errors path="name" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label for="code">Código</label>
            <form:input
                    placeholder="por exemplo: java, python (não use letras maiúsculas, acentos ou caracteres especiais)"
                    id="code" class="form-control" type="text" name="code" value="${subcategoryForm.code}" path="code"/>
            <form:errors path="code" cssClass="alert-danger"/>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" id="active" name="active" ${subcategoryForm.active ? 'checked' : ''}/>
                Subcategoria Ativa?
            </label>
        </div>
        <div class="form-group">
            <label for="order">Ordem da subcategoria</label>
            <form:input placeholder="por exemplo: categoria de ordem 1 aparece antes da subcategoria de ordem 2"
                        type="number" id="order" name="order" min="1" class="form-control"
                        oninput="validity.valid||(value='${subcategoryForm.order}')" path="order"/>
            <form:errors path="order" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label for="explanatoryGuide">Guias de estudo</label>
            <form:textarea placeholder="Um texto apontando para formações para ajudar pessoas perdidas"
                           id="explanatoryGuide" class="form-control" name="studyGuide" rows="4" cols="50"
                           path="explanatoryGuide"></form:textarea>
        </div>
        <div class="form-group">
            <label for="description">Descrição</label>
            <form:input
                    placeholder="por exemplo: Laravel, Cake PHP e CodeIgniter são frameworks que vocẽ vai treinar bastante aqui."
                    id="description" class="form-control" type="text" name="description"
                    value="${subcategoryForm.description}" path="description"/>
        </div>
        <div class="form-group">
            <label for="category">Categoria</label>
            <select id="category" class="form-control" name="categoryId" required>
                <option value="">Selecione</option>
                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}" ${category.name == subcategoryForm.categoryName ? 'selected' : ''}>${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <form:input type="hidden" name="id" value="${subcategoryForm.id}" path="id"/>
            <button type="submit" class="btn btn-primary">Enviar</button>
        </div>
    </form:form>
</div>
</body>
</html>
