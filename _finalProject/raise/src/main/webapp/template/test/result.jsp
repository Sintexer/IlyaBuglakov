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
</head>

<body>
<div class="page">

    <c:url value="/template/parts/header.jsp" var="headerPath"/>
    <jsp:include page="${headerPath}"/>

    <main class="main">
        <div class="section">
            <div class="content">
                <div class="section centered padding-0">
                    <h1 class="page-title"><fmt:message key="test.testing.result.title"/></h1>
                    <div class="breakline"></div>
                    <div class="section wide items-gap-vertical">
                        <a href="/api/test/catalog" class="btn"><fmt:message key="button.go.test.catalog"/></a>
                    </div>
                    <div class="card-lg items-gap-vertical margin-b-2rem">
                        <div class="stack">
                            <div class="test-header stack items-gap-vertical">
                                <h3 class="text-center font-lg">${testName}</h3>
                                <div class="breakline"></div>
                                <h3 class=""><fmt:message key="test.result.result"/>: ${testResult.result}%</h3>
                                <div class="breakline"></div>
                                <h3 class="text-center"><fmt:message key="test.result.incorrect.questions"/>:</h3>

                                <c:if test="${testResult.incorrectQuestions.size() > 0}">
                                    <div id="questions" class="items-gap-vertical stack bg-yellow">
                                        <c:forEach var="question" items="${testResult.incorrectQuestions}">
                                            <div class="question stack items-gap-vertical">
                                                <div class="breakline"></div>
                                                <p class="margin-y-1rem question-header test-text-block">${question.name}</p>
                                                <span class="test-text-block">${question.content}</span>
                                                <ul class="answers margin-l-2rem">
                                                    <c:forEach var="answer" items="${question.answers}">
                                                        <li class="answer items-gap align-items-center">
                                                            <span class="font-md">${answer.content}</span>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:if>
                            </div>
                        </div>
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
