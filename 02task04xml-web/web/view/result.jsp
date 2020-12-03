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
</head>
<body>
<a href="">Home</a>
<a href="./">Back</a>

<h4>Gems</h4>
<table>
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
            <c:choose >
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

</body>
</html>
