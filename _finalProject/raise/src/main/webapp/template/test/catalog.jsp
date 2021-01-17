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
                    <h1 class="page-title">Test catalog</h1>
                    <div class="cards">
                        <c:forEach var="test" items="${testInfos}">
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
                                        <c:forEach var="characteristic" items="${test.characteristics}">
                                            <span>a</span>
                                            <span>${characteristic}</span>
                                        </c:forEach>
                                    </c:if>
                                    <div class="flex-11a"></div>
                                    <div class="breakline"></div>
                                    <div><a class="btn" href="#"><fmt:message key="test.card.button.view"/></a></div>

                                </div>
                                <div class="card-footer">
                                    <span>author:</span>
                                    <a href="#">Ilya Buglakov</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="pagination-wrap">
                        <ul class="pagination">
                            <c:if test="${currentPage>1}">
                                <li><a class="prev link-type" title="previous page">&#10094;</a></li>
                            </c:if>
                            <c:if test="${currentPage>1}">
                                <li>
                                    <a class="btn-round" href="?pageNumber=1">1</a>
                                </li>
                            </c:if>
                            <c:if test="${currentPage>2}">
                                <li>
                                    <span>/</span>
                                </li>
                            </c:if>
                            <c:if test="${currentPage>0}">
                                <li>
                                    <a class="btn-round" href="#">${currentPage-1}</a>
                                </li>
                            </c:if>
                            <li>
                                <a class="btn-round active"  href="#">${currentPage}</a>
                            </li>
                            <c:if test="${currentPage < maxPage}">
                                <li>
                                    <a class="btn-round" href="#">${currentPage+1}</a>
                                </li>
                            </c:if>
                            <c:if test="${currentPage < maxPage-2}">
                                <li>
                                    <span>/</span>
                                </li>
                            </c:if>
                            <c:if test="${currentPage < maxPage-1}">
                                <li>
                                    <a class="btn-round" href="#">${maxPage}</a>
                                </li>
                            </c:if>
                            <c:if test="${currentPage < maxPage}">
                                <li><a class="next link-type" title="next page">&#10095;</a></li>
                            </c:if>

                        </ul>
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
