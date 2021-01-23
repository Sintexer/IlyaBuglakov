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
<%@ taglib prefix="ct" uri="/WEB-INF/customlib.tld" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<fmt:setBundle basename="/locale/header"/>

<script src="<c:url value="/script/modal.js" />"></script>
<script src="<c:url value="/script/changeLocale.js"/>"></script>

<header id="page-header" class="page-header">
    <div>
        <nav class="dec-pancake section bg-none">
            <div class="dec-pancake items-gap-right bold">
                <div class="font-xl">
                    <a href="<ct:link key="root"/>">
                        <h3 class="font-xl">Raise</h3>
                    </a>
                </div>

                <a href="<ct:link key="root"/>"><fmt:message key="link.home"/></a>
                <a href="<ct:link key="test.catalog"/>"><fmt:message key="link.tests"/></a>
                <a href="<ct:link key="test.creator"/>"><fmt:message key="link.creator"/></a>
                <shiro:authenticated>
                    <a href="<ct:link key="profile"/>"><fmt:message key="link.profile"/></a>
                </shiro:authenticated>
                <shiro:hasPermission name="admin:*">
                    <a class="btn btn-yellow" href="<ct:link key="admin.test.catalog"/>"><fmt:message key="link.admin.catalog"/></a>
                </shiro:hasPermission>

            </div>
            <div class="header-right-side dec-pancake items-gap-sm">
                <shiro:guest>
                    <a class="btn" href="<ct:link key="login"/>"><fmt:message key="link.login"/></a>
                    <a class="btn" href="<ct:link key="registration"/>"><fmt:message key="link.registration"/></a>
                </shiro:guest>
                <h:locale/>
            </div>
        </nav>
    </div>
</header>

<div id="langModal" class="modal centered">

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
