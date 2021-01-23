<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Neonl
  Date: 21.12.2020
  Time: 14:17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8">
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Please sign in</title>
</head>
<body>
<div th:if="${registrationFailed}" class="alert">
    <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
    User with email <span th:text="${registrationFailed}"></span> already exist.
</div>
<div class="page">
    <div class="header-logo centered">
        <a href="/">
            <h3>Raise</h3>
        </a>
    </div>
    <div class="dec-pancake">
        <form class="form-card card-md bg-lightcyan" method="post" action="<c:url value="/"/>" >
            <h2 class="form-sign-in-heading">Sign up</h2>
            <input type="email" id="username" name="username" class="form-input rounded-10" placeholder="Your Email" required
                   autofocus>
            <input type="name" id="name" name="name" class="form-input rounded-10" placeholder="Your Name" required
                   >
            <input type="surname" id="surname" name="surname" class="form-input rounded-10" placeholder="Your Surname" required
                   >
            <input type="password" id="password" name="password" class="form-input rounded-10" placeholder="Your Password"
                   required>
            <button class="btn mg-top-2rem" type="submit">SIGN UP</button>
        </form>
    </div>
</div>
</body>
</html>