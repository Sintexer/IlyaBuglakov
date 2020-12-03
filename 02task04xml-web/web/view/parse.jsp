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
</head>
<body>
<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>
<form method="post" action="parse" enctype="multipart/form-data">
    <select name="parserType">
        <c:forEach var="type" items="${types}">
            <option>${type}</option>
        </c:forEach>
    </select>
    Choose a file: <input type="file" name="xml" />
    <input type="submit" value="Upload" />
</form>

</body>
</html>
