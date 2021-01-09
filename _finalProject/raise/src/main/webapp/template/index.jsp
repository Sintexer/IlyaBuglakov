<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>

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

    <h:header></h:header>

    <main class="main">
        <div class="section">
            <div class="content centered">
                <p><fmt:message key="home.title"/></p>
                <a href="api/registration">Registration</a>
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
