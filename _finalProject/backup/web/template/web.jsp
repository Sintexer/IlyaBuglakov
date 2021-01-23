<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <header class="page-header">
        <div>
            <nav class="dec-pancake items-gap">
                <div class="header-logo">
                    <a href="<c:url value="/"/>">
                        <h3>WEB Raise</h3>
                    </a>
                </div>
                <a href="<c:url value="/"/>">Home</a>
            </nav>
        </div>
    </header>

    <main class="main">
        <div class="section">
            <div class="content centered">
                <p>Welcome to WEB</p>
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
