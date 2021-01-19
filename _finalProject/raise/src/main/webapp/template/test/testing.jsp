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
                    <h1 class="page-title"><fmt:message key="test.testing.title"/></h1>
                    <div class="card-lg items-gap-vertical margin-b-2rem">
                        <form class="stack">
                            <div class="test-header stack items-gap-vertical">
                                <h3 class="text-center font-lg">${test.testName}</h3>
                                <input type="number" id="testId" name="testId" hidden value="${test.id}">
                                <div class="breakline"></div>
                                <span><fmt:message key="test.card.difficulty"/>: ${test.difficulty}</span>

                                <c:if test="${not empty test.characteristics}">
                                    <span><fmt:message key="test.characteristics"/>:</span>
                                    <c:forEach var="characteristic" items="${test.characteristics}">
                                        <span>${characteristic}</span>
                                    </c:forEach>
                                </c:if>
                                <%--                                TODO characteristics--%>
                                <div id="questions" class="items-gap-vertical stack">
                                    <c:forEach var="question" varStatus="loop" items="${test.questions}">
                                        <div class="question stack items-gap-vertical">
                                            <div class="breakline"></div>
                                            <h4><fmt:message key="test.question"/> ${loop.index+1}</h4>
                                            <input class="questionId" type="number" name="questionId" hidden
                                                   value="${question.id}">
                                            <p class="margin-y-1rem question-header test-text-block">${question.name}</p>
                                            <span class="test-text-block">${question.content}</span>

                                            <ul class="answers margin-l-2rem">
                                                <c:forEach var="answer" items="${question.answers}">
                                                    <li class="answer items-gap align-items-center">
                                                        <input class="answerId" type="number" hidden
                                                               value="${answer.id}">
                                                        <c:choose>
                                                            <c:when test="${question.correctAmount > 1}">
                                                                <input class="answerOption" type="checkbox"
                                                                       name="${loop.index}">
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input class="answerOption" type="radio"
                                                                       name="${loop.index}">
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <span class="font-md">${answer.content}</span>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="breakline"></div>
                            <button type="button" class="btn margin-t-2rem" onclick="createResponseObject()"><fmt:message
                                    key="test.testing.finish"/></button>
                        </form>
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
