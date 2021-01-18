<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<fmt:setLocale value="${cookie.userLocale.value}" scope="application"/>
<fmt:setBundle basename="/locale/page"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    <meta charset="UTF-8">
    <title>Raise - 505</title>
</head>
<body>
<div class="page">
    <c:url value="/template/parts/header.jsp" var="headerPath"/>
    <jsp:include page="${headerPath}"/>
    <main class="main centered">
        <div class="centered error-msg">
            <h2 class="error-code">404</h2>
            <h2><fmt:message key="error.505" </h2>
            <a class="btn" href="/api/home"><fmt:message key="link.back.main" /></a>
        </div>
    </main>
    <c:url value="/template/parts/footer.jsp" var="footerPath"/>
    <jsp:include page="${footerPath}"/>
</div>
</body>
</html>