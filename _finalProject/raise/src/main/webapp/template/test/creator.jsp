<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<fmt:setLocale value="${cookie.userLocale.value}" scope="application"/>
<fmt:setBundle basename="/locale/page"/>
<%--
  Created by IntelliJ IDEA.
  User: Neonl
  Date: 03.12.2020
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head onload="initScript(256, 256, 512, 512, 3)">
    <title>Raise</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    <link type="text/javascript" href="<c:url value="/script/testCreator.js"/>"/>
    <script src=<c:url value="/script/testCreator.js"/>></script>
    <link type="text/javascript" href="<c:url value="/script/modal.js"/>"/>
    <script src=<c:url value="/script/modal.js"/>></script>
</head>
<body>
<div class="page">

    <c:url value="/template/parts/header.jsp" var="headerPath"/>
    <jsp:include page="${headerPath}"/>

    <main class="main">
        <div class="section">
            <div class="content">
                <div class="section centered padding-0">
                    <h1 class="page-title"><fmt:message key="test.creator.page.title"/></h1>
                    <div class="margin-b-2rem padding-2rem">
                        <span class="font-md"><fmt:message key="test.creator.guide"/></span>
                    </div>
                    <div class="card-md-resizable items-gap-vertical margin-b-2rem">
                        <form class="stack items-gap-vertical">
                            <span class="flex align-items-center padding-1rem">
                                <fmt:message key="test.creator.testname"/>:
                                <input class="flex-auto form-input w-auto" type="text" name="testName"
                                       onchange="validateTestNode()"
                                       pattern="^[^\d',.-][^\n_!¡?÷¿\/\\+=@#$%ˆ&*(){}|~<>;:\[\]]{2,}$" required/>
                            </span>
                            <div class="items-middle flex flex-wrap padding-1rem" id="characteristics">
                                <span><fmt:message key="test.creator.characteristics.title"/></span>
                                <c:forEach var="characteristic" items="${characteristics}">
                                    <div>
                                        <span>
                                            <input type="checkbox" onchange="validateTestNode()"
                                                   value="${characteristic}" name="characteristic">
                                            <fmt:message key="${characteristic.getPropertyName()}"/>
                                        </span>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="breakline"></div>
                            <div class="stack items-gap-vertical" id="questions">
                            </div>
                            <button class="btn btn-yellow margin-b-2rem" type="button"
                                    onclick="addQuestion(this, '<fmt:message key="test.creator.question.title"/>',
                                            '<fmt:message key="test.creator.button.add.answer"/>',
                                            '<fmt:message key="test.creator.button.correct"/>',
                                            '<fmt:message key="test.creator.button.delete.question"/>',
                                            '<fmt:message key="test.creator.question.name.placeholder"/>',)"
                                    id="addQuestionButton">
                                <fmt:message key="test.creator.button.add.question"/>
                            </button>
                            <div class="breakline"></div>
                            <button class="btn margin-y-1rem" type="button" onclick="sendResult()">
                                <fmt:message key="test.creator.button.save.test"/>
                            </button>
                        </form>
                    </div>
                    <div id="testGuide" class="margin-b-2rem padding-2rem" hidden>
                        <span class="font-md"><fmt:message key="test.creator.guide"/></span>
                    </div>

                </div>
            </div>
        </div>
    </main>

    <div id="testPostModal" class="modal centered">

        <!-- Модальное содержание -->
        <div class="modal-content card-mk">
            <div class="modal-header bold">
                <h2 class="margin-right-auto">Are you sure?</h2>
                <span class="close-btn" onclick="closeModal('testPostModal')">&times;</span>
            </div>
            <div class="items-gap dec-pancake bold">
                <form method="post" action="/api/test/save">
                    <input type="hidden" name="testJson" id="testJson">
                    <button type="submit" class="btn">Save</button>
                </form>
            </div>
        </div>

    </div>

    <c:url value="/template/parts/footer.jsp" var="footerPath"/>
    <jsp:include page="${footerPath}"/>

</div>
</body>
</html>
