<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    <meta charset="UTF-8">
    <title>Error has occurred</title>
</head>
<body>
<div class="page">
    <div class="header-logo section centered">
        <a href="/">
            <h3>Elevate</h3>
        </a>
    </div>
    <main class="main centered">
        <div class="centered error-msg">
            <h2 class="error-code">${error}</h2>
            <h2>An error has occurred</h2>
            <a class="btn" href="/api/home">Back to main</a>
        </div>
    </main>
    <c:url value="/template/parts/footer.jsp" var="footerPath"/>
    <jsp:include page="${footerPath}" />
</div>
</body>
</html>