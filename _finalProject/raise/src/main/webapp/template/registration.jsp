<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${cookie.userLocale.value}" scope="application"/>
<fmt:setBundle basename="/locale/page" var="page"/>
<fmt:setBundle basename="/locale/form" var="form"/>
<%--
  Created by IntelliJ IDEA.
  User: Neonl
  Date: 21.12.2020
  Time: 14:17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8">
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    <title>Registration</title>
</head>
<body>

<fmt:message key="reg.user.email.tooltip" var="emailTooltip" bundle="${form}"/>
<fmt:message key="reg.user.name.tooltip" var="nameTooltip" bundle="${form}"/>
<fmt:message key="reg.user.surname.tooltip" var="surnameTooltip" bundle="${form}"/>
<fmt:message key="reg.user.password.tooltip" var="passwordTooltip" bundle="${form}"/>

<c:set var="emailLength" value="${emailLength}" scope="page"/>
<c:set var="nameLength" value="${nameLength}" scope="page"/>
<c:set var="surnameLength" value="${surnameLength}" scope="page"/>
<c:set var="passwordMax" value="${passwordMax}" scope="page"/>
<c:set var="passwordMin" value="${passwordMin}" scope="page"/>

<c:set var="emailTooltipFormatted" value="${fn:replace(emailTooltip, \"{0}\", emailLength)}" scope="page"/>
<c:set var="nameTooltipFormatted" value="${fn:replace(nameTooltip, \"{0}\", nameLength)}" scope="page"/>
<c:set var="surnameTooltipFormatted" value="${fn:replace(surnameTooltip, \"{0}\", surnameLength)}" scope="page"/>
<c:set var="passwordTooltipReplaced" value="${fn:replace(passwordTooltip, \"{0}\", passwordMin)}" scope="page"/>
<c:set var="passwordTooltipFormatted" value="${fn:replace(passwordTooltipReplaced, \"{1}\", passwordMax)}" scope="page"/>

<div class="page">
    <c:url value="/template/parts/header.jsp" var="headerPath"/>
    <jsp:include page="${headerPath}"/>

    <div class="stack centered">
        <c:if test="${registrationFailed}">
            <div class="alert">
                <span class="close-btn" onclick="this.parentElement.style.display='none';">&times;</span>
                <c:if test="${userEmailAlreadyExist}">
                    <fmt:message key="error.emailduplicate" bundle="${form}"/>
                </c:if>
                <c:if test="${invalidEmail}">
                    ${emailTooltipFormatted}
                </c:if>
                <c:if test="${invalidName}">
                    ${nameTooltipFormatted}
                </c:if>
                <c:if test="${invalidSurname}">
                    ${surnameTooltipFormatted}
                </c:if>
                <c:if test="${invalidPassword}">
                    ${passwordTooltipFormatted}
                </c:if>
            </div>
        </c:if>
        <form class="form-card card-md bg-lightcyan" method="post" action="<c:url value="/api/registration"/>">
            <div class="text-break-all text-center">
                <h2 class="form-sign-in-heading"><fmt:message key="signup" bundle="${page}"/></h2>
            </div>

            <div class="tooltip rounded-10">
                <span class="tooltip-text rounded-10">${emailTooltipFormatted}</span>
                <input type="email"
                       maxlength="${emailLength}"
                       id="username"
                       name="username"
                       class="form-input rounded-10"
                       placeholder=
                       <fmt:message key="placeholder.email" bundle="${form}"/>
                               required autofocus
                       value="${emailPrevVal}">
            </div>

            <div class="tooltip rounded-10">
                <span class="tooltip-text rounded-10">${nameTooltipFormatted}</span>
                <input type="name"
                       maxlength="${nameLength}"
                       id="name"
                       name="name"
                       class="form-input rounded-10"
                       placeholder=
                       <fmt:message key="placeholder.name" bundle="${form}"/>
                               required value="${namePrevVal}">
            </div>

            <div class="tooltip rounded-10">
                <span class="tooltip-text rounded-10">${surnameTooltipFormatted}</span>
                <input type="surname"
                       maxlength="${surnameLength}"
                       id="surname"
                       name="surname"
                       class="form-input rounded-10"
                       placeholder=
                       <fmt:message key="placeholder.surname" bundle="${form}"/>
                               required value="${surnamePrevVal}">
            </div>

            <div class="tooltip rounded-10">
                <span class="tooltip-text rounded-10">${passwordTooltipFormatted}</span>
                <input type="password"
                       minlength="${passwordMin}"
                       maxlength="${passwordMax}"
                       id="password"
                       name="password"
                       class="form-input rounded-10"
                       placeholder=
                       <fmt:message key="placeholder.password" bundle="${form}"/>
                               required>
            </div>

            <button class="btn mg-top-2rem" type="submit"><fmt:message key="button.signup" bundle="${page}"/></button>
        </form>
    </div>

    <c:url value="/template/parts/footer.jsp" var="footerPath"/>
    <jsp:include page="${footerPath}" />

</div>
</body>
</html>