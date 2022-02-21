<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="title" value="${courseForm.code == null ? 'Novo Curso' : 'Editar Curso'}"></c:set>
<c:set var="actionCourseUrl" value="${courseForm.id == null ? '/admin/courses/new' : '/admin/courses/'
.concat(category.code).concat('/').concat(subcategory.code).concat('/').concat(courseForm.code)}"></c:set>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>

    <title>${title}</title>
</head>
<body>
<div class="container">
    <form:form modelAttribute="courseForm" action="${actionCourseUrl}" method="post">
        <h2>${title}</h2>
        <div class="form-group">
            <label for="name">Nome</label>
            <form:input placeholder="Digite aqui o nome do Curso"
                        type="text" id="name" class="form-control" name="name" value="${courseForm.name}"
                        path="name"/>
            <form:errors path="name" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label for="code">Código</label>
            <form:input
                    placeholder="por exemplo: git-e-github-para-sobrevivencia, java-e-persistencia (não use letras maiúsculas, acentos ou caracteres especiais)."
                    id="code" class="form-control" type="text" name="code" value="${courseForm.code}" path="code"/>
            <form:errors path="code" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label for="estimatedTimeToFinish">Tempo de finalização</label>
            <form:input placeholder="tempo para finalização do curso (o curso deve ter de 1 a 20 horas)."
                        type="number" id="estimatedTimeToFinish" name="estimatedTimeToFinish" min="1" max="20"
                        class="form-control"
                        oninput="validity.valid||(value='${courseForm.estimatedTimeToFinish}')"
                        path="estimatedTimeToFinish"/>
            <form:errors path="estimatedTimeToFinish" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label for="visibility">Visibilidade</label>
            <select id="visibility" class="form-control" name="visibility">
                <c:forEach items="${visibility}" var="courseVisibility">
                    <option value="${courseVisibility}" ${courseVisibility.equals(courseForm.visibility) ? 'selected' : ''} >${courseVisibility.description}</option>
                </c:forEach>
            </select>
            <form:errors path="visibility"/>
        </div>
        <div class="form-group">
            <label for="code">Público alvo</label>
            <form:input
                    placeholder="para quem é este curso."
                    id="targetAudience" class="form-control" type="text" name="targetAudience"
                    value="${courseForm.targetAudience}"
                    path="targetAudience"/>
            <form:errors path="targetAudience" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label for="instructorName">Nome do instrutor</label>
            <form:input
                    placeholder="nome do instrutor que irá ministrar este curso."
                    id="instructorName" class="form-control" type="text" name="instructorName" value="${courseForm.instructorName}" path="instructorName"/>
            <form:errors path="instructorName" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label for="summary">Ementa</label>
            <form:textarea placeholder="insira uma descrição detalhada do que será abordado neste curso."
                           value="${courseForm.summary}"
                           id="summary" class="form-control" name="summary" rows="4" cols="50"
                           path="summary"></form:textarea>
        </div>
        <div class="form-group">
            <label for="learnedSkills">Habilidades desenvolvidas</label>
            <form:input
                    placeholder="um texto sobre quais capacidades a pessoa que faz o curso terá exercitado."
                    id="learnedSkills" class="form-control" type="text" name="learnedSkills"
                    value="${courseForm.learnedSkills}" path="learnedSkills"/>
        </div>
        <div class="form-group">
            <label for="subcategory">Subcategoria</label>
            <select id="subcategory" class="form-control" name="subcategoryId" required>
                <option value="">Selecione</option>
                <c:forEach items="${subcategories}" var="subcategory">
                    <option value="${subcategory.id}" ${subcategory.name == courseForm.subcategoryName ? 'selected' : ''}>${subcategory.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <form:input type="hidden" name="id" value="${courseForm.id}" path="id"/>
            <button type="submit" class="btn btn-primary">Enviar</button>
        </div>
    </form:form>
</div>
</body>
</html>
