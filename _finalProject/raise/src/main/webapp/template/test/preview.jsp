<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${cookie.userLocale.value}" scope="application"/>
<fmt:setBundle basename="/locale/page"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Raise</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    <link type="text/javascript" href="<c:url value="/script/testing.js"/>"/>
    <script src=<c:url value="/script/testing.js"/>></script>
</head>

<body>
<div class="page">

    <c:url value="/template/parts/header.jsp" var="headerPath"/>
    <jsp:include page="${headerPath}"/>

    <main class="main">
        <div class="section">
            <div class="content">
                <div class="section centered padding-0">
                    <h1 class="page-title"><fmt:message key="test.preview.title"/></h1>
                    <div class="breakline"></div>
                    <h2 class="margin-y-1rem font-md">${test.testName}</h2>
                    <div class="card-md items-gap-vertical margin-b-2rem">
                        <span><fmt:message key="test.card.difficulty"/>: ${test.difficulty}</span>
                        <c:if test="${not empty test.characteristics}">
                            <span><fmt:message key="test.characteristics"/>:</span>
                            <c:forEach var="characteristic" items="${test.characteristics}">
                                <span>${characteristic}</span>
                            </c:forEach>
                        </c:if>
                        <div class="breakline"></div>
                        <span><fmt:message key="test.questions.amount"/>: ${test.questions.size()}</span>
                        <div class="breakline"></div>
                        <a href="/api/test/testing?testId=${test.id}" class="btn"><fmt:message key="test.button.testing"/></a>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <c:url value="/template/parts/footer.jsp" var="footerPath"/>
    <jsp:include page="${footerPath}"/>

</div>
</body>
</html>
