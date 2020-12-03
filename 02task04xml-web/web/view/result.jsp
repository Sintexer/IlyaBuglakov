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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/WEB-INF/style/style.css">
</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">XML Parser</a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/02task04xml_web_war_exploded/">Home<span class="sr-only"></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./">Parse</a>
            </li>
        </ul>
    </div>
</nav>
<body>
<div class="body-wrap">
    <div class="content container-fluid">
        <p>Result of xml parsed objects represented in table below</p>
        <a href="./">Back</a>

        <h4>Gems</h4>
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
</body>
</html>
