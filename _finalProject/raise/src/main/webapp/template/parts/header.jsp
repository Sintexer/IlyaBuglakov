<%--
  Created by IntelliJ IDEA.
  User: Neonl
  Date: 12.01.2021
  Time: 19:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>

<fmt:setBundle basename="/locale/header"/>

<%--<head>--%>
<%--    <link rel="script" href="<c:url value="/script/modal.js"/>" >--%>
<%--</head>--%>

<script src="/script/modal.js"></script>
<script src="/script/changeLocale.js"></script>

<header id="page-header" class="page-header">
    <div>
        <nav class="dec-pancake items-gap">
            <div class="dec-pancake items-gap bold">
                <div class="header-logo">
                    <a href="/api/home">
                        <h3>Raise</h3>
                    </a>
                </div>
                <a href="/api/home"><fmt:message key="link.home"/></a>
                <a href="/api/registration"><fmt:message key="link.profile"/></a>
            </div>
            <div class="header-right-side">
                <h:locale/>
            </div>
        </nav>
    </div>
</header>

<!-- Модальный -->
<div id="langModal" class="modal centered">

    <!-- Модальное содержание -->
    <div class="modal-content card-mk">
        <div class="modal-header bold">
            <h2 class="margin-right-auto"><fmt:message key="title.modal.lang"/></h2>
            <span class="close-btn" onclick="closeModal('langModal')">&times;</span>
        </div>
        <div class="items-gap dec-pancake bold">
            <c:forEach var="locale" items="${locales}">
                <a class="link-type" onclick="sendLocale('${locale.locale}')">${locale.viewName}</a>
            </c:forEach>
        </div>
    </div>

</div>
