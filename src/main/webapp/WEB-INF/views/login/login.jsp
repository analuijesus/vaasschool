<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/loginStyle.css" rel="stylesheet">

    <title>Login | Cursos online de tecnologia</title>
</head>
<body>
<main class="container">
    <section class="auth">
        <section class="login">
            <h1 class="login__title">Já estuda com a gente?</h1>
            <p class="login__subtitle">Faça seu login e boas aulas!</p>
            <form class="login__form" action="/login" method="POST">
                <label for="login__email">E-mail</label>
                <input type="email" name="username" id="login-email" autofocus>
                <label for="login__password">Senha</label>
                <input type="password" name="password" id="login-password" autocomplete="off">
                <button class="login__button" type="submit">Entrar</button>
            </form>
        </section>
        <section class="signup">
            <h2 class="signup__title">Ainda não estuda com a gente?</h2>
            <p class="signup__text">São mais de mil cursos nas seguintes áreas</p>

            <ul class="categories">
                <c:forEach items="${categories}" var="category">
                    <li class="category-card">
                        <a class="category-card__link" href="/category/${category.code}">
                            <span class="category-card__icon">
                                <img src="${category.imagePath}">
                            </span>
                            <h3 class="category-card__title">${category.name}</h3>

                            <c:forEach items="${category.subcategoryLoginDtos}" var="subcategory">
                                <a href="/${category.code}" class="category-card__details">${subcategory.name},</a>
                            </c:forEach>
                            <p class="category-card__details">
                                <c:if test="${category.subcategorySize > 3}">
                                    e mais...
                                </c:if>
                            </p>
                        </a>
                    </li>
                </c:forEach>
            </ul>

        </section>
    </section>
</main>
</body>
</html>