<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Neonl
  Date: 03.12.2020
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parsing result</title>
    <style>
        <%@include file="/WEB-INF/style/style.css"%>
    </style>
</head>
<%--<nav class="navbar navbar-expand-lg navbar-light bg-light">--%>
<%--    <a class="navbar-brand" href="#">XML Parser</a>--%>

<%--    <div class="collapse navbar-collapse" id="navbarSupportedContent">--%>
<%--        <ul class="navbar-nav mr-auto">--%>
<%--            <li class="nav-item active">--%>
<%--                <a class="nav-link" href="/02task04xml_web_war_exploded/">Home<span class="sr-only"></span></a>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="./">Parse</a>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--    </div>--%>
<%--</nav>--%>
<%--<body>--%>
<%--<div class="body-wrap">--%>
<%--    <div class="content container-fluid">--%>
<%--        <p>Result of xml parsed objects represented in table below</p>--%>
<%--        <a href="./">Back</a>--%>

<%--        <h4>Gems</h4>--%>
<%--        --%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>

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

    <main class="main centered">
        <div class="section-lg centered">
            <div class="content">
                <table class="gem-table">
                    <tr>
                        <th>Name</th>
                        <th>Color</th>
                        <th>Transparency</th>
                        <th>Faces number</th>
                        <th>Mineral kind</th>
                        <th>Preciousness</th>
                        <th>Origin</th>
                        <th>Mined date</th>
                        <th>Certificate id</th>
                        <th>Certificate organisation</th>
                        <th>Certificate date</th>
                        <th>Special reason</th>
                        <th>Weight</th>
                    </tr>
                    <c:forEach var="gem" items="${gems}">
                        <tr>
                            <td>${gem.name}</td>
                            <td>${gem.visualParameters.color}</td>
                            <td>${gem.visualParameters.transparency}</td>
                            <td>${gem.visualParameters.facesNumber}</td>
                            <td>${gem.mineral}</td>
                            <td>${gem.preciousness}</td>
                            <td>${gem.origin}</td>
                            <td>${gem.minedDate}</td>
                            <td>${gem.certificate.certificateId}</td>
                            <td>${gem.certificate.organisation}</td>
                            <td>${gem.certificate.receivingDate}</td>
                            <c:choose>
                                <c:when test="${gem.certificate['class'].simpleName eq 'SpecialCertificate'}">
                                    <td>${gem.certificate.specialReason}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>-</td>
                                </c:otherwise>
                            </c:choose>
                            <td>${gem.weight}</td>
                        </tr>
                    </c:forEach>
                </table>
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
