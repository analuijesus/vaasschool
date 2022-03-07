<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>

<template:list-admin-templates title="Subcategorias">
    <h4>${categoryName}</h4>
    <h2>Subcategorias</h2>

    <a href="/admin/subcategories/new">
        <button type="button" class="btn btn-primary my-4">Nova Subcategorias</button>
    </a>
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Código</th>
                <th colspan="3">Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${subcategories}" var="subcategory">
                <tr>
                    <td>${subcategory.name}</td>
                    <td>${subcategory.code}</td>
                    <c:choose>
                        <c:when test="${subcategory.active eq true}">
                            <td id="category_${subcategory.id}">
                                <a href="#" onClick="deactivateSubcategory(${subcategory.id})">
                                    Ativa
                                </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>Inativa</td>
                        </c:otherwise>
                    </c:choose>
                    <td><a href="/admin/courses/${categoryCode}/${subcategory.code}"><u>Cursos</u></a></td>
                    <td><a href="/admin/subcategories/${categoryCode}/${subcategory.code}">
                        <button type="button" class="btn btn-light"
                                href="/admin/categories/${categoryCode}/${subcategory.code}">Editar
                        </button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</template:list-admin-templates>