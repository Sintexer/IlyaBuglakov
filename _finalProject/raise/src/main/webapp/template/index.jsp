<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="ct" uri="/WEB-INF/customlib.tld" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%--<fmt:setLocale value="${cookie.userLocale.value}"/>--%>
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
<head>
    <title><fmt:message key="title.root"/></title>
    <c:url value="/template/parts/head-static.jsp" var="headPath"/>
    <jsp:include page='${headPath}'/>
</head>
<body>
<div class="page">

    <c:url value="/template/parts/header.jsp" var="headerPath"/>
    <jsp:include page="${headerPath}"/>


    <main class="main">
        <div class="padding-0 bg-none">
            <div class="landing-section noise-bg">

                <div class="section banner bg-none">
                    <div class="flex w-100">
                        <div class="centered landing-img-padding">

                            <img class="landing-img" src="<c:url value="/img/astronaut.png"/>"/>
                        </div>
                        <div class="f-c-center">
                            <h1 class="landing-font-main p-y-1rem"><fmt:message key="landing.title" /></h1>
                            <h2 class="landing-font-sub p-y-1rem"><fmt:message key="landing.motto"/></h2>
                            <div class="flex p-y-1rem">
                                <a href="<ct:link key="registration"/>" class="btn margin-r-2rem"><fmt:message key="button.register"/></a>
                                <a href="<ct:link key="test.catalog"/>" class="btn btn-filled"><fmt:message key="button.catalog"/></a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="stack">
            <div class="section">
                <div class="land-card p-y-1rem box-100">
                    <div class="info">
                        <h3 class="title"><fmt:message key="landing.evolve"/></h3>
                        <span><fmt:message key="landing.evolve.info" /></span>
                    </div>
                    <div class="land-card-img centered">
                        <img src="<c:url value="/img/test-icon.svg"/>">
                    </div>

                </div>
            </div>
            <div class="breakline"></div>
            <div class="bg-primary">
                <div class="section bg-none">
                    <div class="land-card p-y-1rem box-100">
                        <div class="land-card-img centered">
                            <img src="<c:url value="/img/constructor.svg"/>">
                        </div>
                        <div class="info">
                            <h3 class="title"><fmt:message key="landing.constructor" /></h3>
                            <div>
                            <span class="info-text"><fmt:message key="landing.creator.info" /></span>
                            </div>
                            <a class="btn btn-black"><fmt:message key="button.create.test" /></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="breakline"></div>
            <div class="section">
                <div class="land-card p-y-1rem box-100">
                    <div class="info">
                        <h3 class="title"><fmt:message key="landing.progression" /></h3>
                        <span><fmt:message key="landing.progression.info" /></span>
                        <p class="margin-t-2rem"><fmt:message key="landing.progression.sub.info" /></p>
                    </div>

                    <div class="land-card-img centered">
                        <img src="<c:url value="/img/progress.svg"/>">
                    </div>

                </div>

            </div>
            <div class="breakline"></div>
            <div class="bg-gray centered">
                <h3 class="font-lg font-ubuntu">Characteristics</h3>
            </div>
            <div class="breakline"></div>
            <div class="section">
                <div class="land-card p-y-1rem box-100">
                    <div class="land-card-img centered">
                        <img src="<c:url value="/img/memory.svg"/>">
                    </div>
                    <div class="info">
                        <h3 class="title"><fmt:message key="landing.memory" /></h3>
                        <span><fmt:message key="landing.memory.info" /></span>
                    </div>
                </div>
            </div>
            <div class="breakline"></div>
            <div class="section">
                <div class="land-card p-y-1rem box-100">
                    <div class="land-card-img centered">
                        <img src="<c:url value="/img/logic.svg"/>">
                    </div>
                    <div class="info">
                        <h3 class="title"><fmt:message key="landing.logic" /></h3>
                        <span><fmt:message key="landing.logic.info" /></span>
                    </div>
                </div>
            </div>
            <div class="breakline"></div>
            <div class="section">
                <div class="land-card p-y-1rem box-100">
                    <div class="land-card-img centered">
                        <img src="<c:url value="/img/calculations.svg"/>">
                    </div>
                    <div class="info">
                        <h3 class="title"><fmt:message key="landing.calculations" /></h3>
                        <span><fmt:message key="landing.calculations.info" /></span>
                    </div>
                </div>
            </div>
            <div class="breakline"></div>
            <div class="section">
                <div class="land-card p-y-1rem box-100">
                    <div class="land-card-img centered">
                        <img src="<c:url value="/img/reaction.svg"/>">
                    </div>
                    <div class="info">
                        <h3 class="title"><fmt:message key="landing.reaction" /></h3>
                        <span><fmt:message key="landing.reaction.info" /></span>
                    </div>
                </div>
            </div>
            <div class="breakline"></div>
            <div class="bg-gray centered">
                <div class="dec-pancake items-gap p-y-1rem">
                    <a href="<ct:link key="test.catalog"/>" class="btn btn-filled"><fmt:message key="button.catalog"/></a>
                    <a href="<ct:link key="registration"/>" class="btn btn-black"><fmt:message key="link.register"/></a>
                    <a href="<ct:link key="login"/>" class="link"><fmt:message key="link.login"/></a>
                </div>
            </div>
            <div class="breakline"></div>
            <div class="">
                <div class="section bg-none">
                    <div class="land-card p-y-1rem box-100">
                        <div class="info">
                            <h3 class="title"><fmt:message key="landing.comment" /></h3>
                            <span><fmt:message key="landing.comment.info" /></span>
                        </div>

                        <div class="land-card-img centered">
                            <img src="<c:url value="/img/comments.svg"/>">
                        </div>

                    </div>

                </div>
            </div>
            <div class="breakline"></div>
            <div class="bg-black text-white">
                <div class="section bg-none">
                    <div class="land-card p-y-1rem box-100">
                        <div></div>
                        <div class="info">
                            <h3 class="title"><fmt:message key="landing.start.now" /></h3>
                            <div>
                                <span class="info-text"><fmt:message key="landing.start.now.info" /></span>
                            </div>
                            <div class="jc-left dec-pancake items-gap-sm-right">
                                <a href="<ct:link key="test.catalog"/>" class="btn btn-filled"><fmt:message key="button.catalog"/></a>
                                <a href="<ct:link key="registration"/>" class="btn"><fmt:message key="link.register"/></a>
                                <a href="<ct:link key="login"/>" class="link link-white"><fmt:message key="link.login"/></a>
                            </div>
                        </div>
                    </div>


                </div>
                <div class="box-100 centered">
                    <h3 class="font-mdd">Новые тесты</h3>
                </div>
            </div>
            <div class="breakline"></div>
            <div class="">
                <div class="section bg-none">
                    <div class="land-card p-y-1rem box-100">
                        <div class="info">
                            <h3 class="title"><fmt:message key="landing.impulse" /></h3>
                            <div><span><fmt:message key="landing.impulse.info" /></span>
                            </div>
                            <a href="#" class="btn btn-black"><fmt:message key="button.go.up" /></a>
                        </div>

                        <div class="land-card-img centered">
                            <img src="<c:url value="/img/portal.svg"/>">
                        </div>

                    </div>

                </div>
            </div>
            <div class="section">
            </div>
        </div>
    </main>

    <c:url value="/template/parts/footer.jsp" var="footerPath"/>
    <jsp:include page="${footerPath}"/>

</div>
</body>
</html>
