<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ct" uri="/WEB-INF/customlib.tld" %>

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
        <div class="section stack">
            <div class="content">
                <h1 class="page-title"><fmt:message key="test.preview.title"/></h1>
                <div class="breakline"></div>
                <div class="grid-2 padding-0">
                    <div class="grid-element">
                        <div class="test-preview">
                            <h2 class="margin-y-1rem font-md w-fit m-x-auto">${test.testName}</h2>
                            <div class="card-md box-100 items-gap-vertical margin-b-2rem m-x-auto">
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
                                <a href="<ct:link key="test.testing"/>?testId=${test.id}" class="btn">
                                    <fmt:message key="test.button.testing"/>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="grid-element items-gap-vertical">
                        <h2 class="margin-y-1rem font-md w-fit m-x-auto flex">
                            <c:if test="${currentPage>1}">
                                <form>
                                    <input hidden name="testId" value="${test.id}">
                                    <input hidden name="pageNumber" value="${currentPage-1}">
                                        <button class="prev link-type"
                                           title="previous page">&#10094;</button>
                                </form>
                            </c:if>
                            Comments
                            <c:if test="${currentPage < maxPage}">
                                <form>
                                    <input hidden name="testId" value="${test.id}">
                                    <input hidden name="pageNumber" value="${currentPage+1}">
                                    <button    class="next link-type" title="next page">&#10095;</button>
                                </form>
                            </c:if>
                        </h2>

                        <shiro:authenticated>
                            <form class="items-gap-vertical" action="<ct:link key="test.post.comment"/>" method="post">
                                <input hidden name="testId" value="${test.id}">
                                <textarea minlength="5"
                                          maxlength="512"
                                          placeholder="Your comment..."
                                          class="form-input unresize w-100"
                                          rows="5"
                                          name="comment"></textarea>
                                <button class="btn w-100">Send</button>
                            </form>
                        </shiro:authenticated>

                        <c:forEach var="comment" items="${comments}">
                            <div class="comment">
                                <div class="card-md box-100">
                                    <span>${comment.user.name} ${comment.user.surname}</span>
                                    <span>${comment.timestamp}</span>
                                    <div class="breakline"></div>
                                    <span class="m-t-1rem">${comment.content}</span>
                                </div>
                            </div>
                        </c:forEach>

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
