<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="ct" uri="/WEB-INF/customlib.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${cookie.userLocale.value}" scope="application"/>
<fmt:setBundle basename="/locale/page"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="title.test.catalog"/></title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>"/>

</head>

<body>
<div class="page">

    <c:url value="/template/parts/header.jsp" var="headerPath"/>
    <jsp:include page="${headerPath}"/>

    <main class="main">
        <div class="section">
            <div class="content">
                <div class="section block centered padding-0">
                    <h1 class="page-title"><fmt:message key="title.test.catalog"/></h1>
                    <div class="breakline"></div>
                    <c:url value="/template/parts/pagination.jsp" var="topPagination"/>
                    <jsp:include page="${topPagination}"/>
                    <div class="cards">
                        <c:forEach var="test" items="${tests}">
                            <div class="card">
                                <div class="card-body stack items-gap-vertical">
                                    <h3>${test.testName}</h3>
                                    <div class="breakline"></div>
                                    <span><fmt:message key="test.card.difficulty"/>: </span>
                                    <span class="bold">${test.difficulty}</span>
                                    <span><fmt:message key="test.card.questions.amount"/>:</span>
                                    <span class="bold">${test.questionsAmount}</span>
                                    <c:if test="${not empty test.characteristics}">
                                        <div class="breakline"></div>
                                        <span><fmt:message key="test.characteristics"/>:</span>
                                        <c:forEach var="characteristic" items="${test.characteristics}">
                                            <span>${characteristic}</span>
                                        </c:forEach>
                                    </c:if>
                                    <div class="flex-11a"></div>
                                    <div class="breakline"></div>
                                    <div><a class="btn" href="<ct:link key="test.preview"/>?testId=${test.id}"><fmt:message key="test.card.button.view"/></a></div>

                                </div>
                                <div class="card-footer">
                                    <span>author:</span>
                                    <a href="<ct:link key="user.profile"/>?userId=${test.author.id}">
                                            ${test.author.name} ${test.author.surname}
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <c:url value="/template/parts/pagination.jsp" var="bottomPagination"/>
                    <jsp:include page="${bottomPagination}"/>
                </div>
            </div>
        </div>
    </main>

    <c:url value="/template/parts/footer.jsp" var="footerPath"/>
    <jsp:include page="${footerPath}"/>

</div>
</body>
</html>
