<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Neonl
  Date: 03.12.2020
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parsing XML</title>
    <style>
        <%@include file="/WEB-INF/style/style.css"%>
    </style>
</head>

<body>
<div class="page">

    <header class="page-header">
        <div>
            <nav class="dec-pancake items-gap">
                <div class="header-logo">
                    <a href="/02task04xml_web_war_exploded">
                        <h3>XML Parser</h3>
                    </a>
                </div>
                <a href="/02task04xml_web_war_exploded">Home</a>
                <a href="/02task04xml_web_war_exploded/parse">Parse</a>
            </nav>
        </div>
    </header>

    <main class="main">
        <div class="section">
            <div class="dec-pancake">
                <c:if test="${not empty error}">
                    <p>${error}</p>
                </c:if>

                <form class="form-card card-md bg-lightcyan" method="post" action="parse" enctype="multipart/form-data">
                    Choose parser type:<select class="form-input rounded-10" name="parserType">
                    <c:forEach var="type" items="${types}">
                        <option>${type}</option>
                    </c:forEach>
                </select>
                    Choose a file: <input class="form-input rounded-10" type="file" name="xml" />
                    <button class="btn mg-top-2rem" type="submit" value="Upload">Parse</button>
                </form>
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
