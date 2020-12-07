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
    <title>Home page</title>
    <style>
<%--      TODO c:include
<link rel="stylesheet" href = "<c:url value = "/css/style.css"/>">/>

--%>
      <%@include file="/WEB-INF/style/style.css"%>
    </style>
  </head>
  <body>
  <div class="page">

    <header class="page-header">
      <div>
        <nav class="dec-pancake items-gap">
          <div class="header-logo">
<%--          TODO  c:url

 <a href = "<c:url value = "/jsp/index.htm"/>">TEST</a>
--%>
            <a href="/02task04xml_web_war_exploded">
              <h3>XML Parser</h3>
            </a>
          </div>
          <a href="/02task04xml_web_war_exploded">Home</a>
          <a href="/parse">Parse</a>
        </nav>
      </div>
    </header>

    <main class="main">
      <div class="section">
        <div class="content centered">
          <p>Welcome to xml parser</p>
          <a class="btn" href="./parse">Parse</a>
        </div>
      </div>
    </main>

    <footer>
      <div class="page-footer centered">
        <div>
          <h4>
            © 2020 Copyright
            Ilya Buglakov
          </h4>
        </div>
      </div>
    </footer>
  </div>
  </body>
</html>
