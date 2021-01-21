<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="ct" uri="/WEB-INF/customlib.tld" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

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
        <div class="main-head">
            <div class="section bg-none block">
                <div class="head-info">
                    <h2 class="bold font-lg">Ilya Buglakov</h2>
                    <p>Registered: 22-01-2021</p>
                </div>
                <div class="dec-pancake jc-space-around">
                    <div class="user-achievements text-center">
                        <h3 class="font-lg">22</h3>
                        <span>test passed</span>
                    </div>
                    <div class="user-achievements text-center">
                        <h3 class="font-lg">33</h3>
                        <span>test created</span>
                    </div>
                    <div class="user-achievements text-center">
                        <h3 class="font-lg">44</h3>
                        <span>comment posted</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="content">
                <span >User characteristics:</span>
                <div class="breakline"></div>
                <div class="characteristic-section padding-2rem">
                    <div>Memory: 200</div>
                    <span>Mem memwemr mfmw mfmgkdm kmk mfghmgfmhi mfih dfihmdfihm idfmhi dfih fihm fihmdifhm idfmh </span>
                </div>
                <div class="breakline"></div>
                <div class="characteristic-section padding-2rem">
                    <div>Memory: 200</div>
                    <span>Mem memwemr mfmw mfmgkdm kmk mfghmgfmhi mfih dfihmdfihm idfmhi dfih fihm fihmdifhm idfmh </span>
                </div>
                <div class="breakline"></div>
                <div class="characteristic-section padding-2rem">
                    <div>Memory: 200</div>
                    <span>Mem memwemr mfmw mfmgkdm kmk mfghmgfmhi mfih dfihmdfihm idfmhi dfih fihm fihmdifhm idfmh </span>
                </div>
                <div class="breakline"></div>
                <div class="characteristic-section padding-2rem">
                    <div>Memory: 200</div>
                    <span>Mem memwemr mfmw mfmgkdm kmk mfghmgfmhi mfih dfihmdfihm idfmhi dfih fihm fihmdifhm idfmh </span>
                </div>


            </div>
        </div>
    </main>

    <c:url value="/template/parts/footer.jsp" var="footerPath"/>
    <jsp:include page="${footerPath}"/>

</div>
</body>
</html>
