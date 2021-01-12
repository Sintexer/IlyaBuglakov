<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="/locale/page"/>
<%--
  Created by IntelliJ IDEA.
  User: Neonl
  Date: 03.12.2020
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Raise</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
</head>
<body>
<div class="page">

    <jsp:include page="parts/header.jsp"/>

    <main class="main">
        <div class="section">
            <div class="content centered">
                <div class="section"><p><fmt:message key="home.title"/></p></div>
                <a href="/api/registration">Registration</a>
                <p>Hi <shiro:notAuthenticated>Guest</shiro:notAuthenticated></p>
                <shiro:authenticated><p>HELLO</p></shiro:authenticated>
            </div>
        </div>
    </main>

    <footer>
        <div class="page-footer centered">
            <div>
                <h4>
                    © 2020 Copyright
                    Ilya Buglakov
                </h4>
            </div>
        </div>
    </footer>
</div>
</body>
</html>
