<%--
  Created by IntelliJ IDEA.
  User: Neonl
  Date: 12.01.2021
  Time: 19:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="/locale/header"/>
<header id="page-header" class="page-header">
    <div>
        <nav class="dec-pancake items-gap">
            <div class="header-logo">
                <a href="/api/home">
                    <h3>Raise</h3>
                </a>
            </div>
            <a href="/api/home"><fmt:message key="link.home"/></a>
            <a href="/api/registration"><fmt:message key="link.profile"/></a>
        </nav>
    </div>
</header>
