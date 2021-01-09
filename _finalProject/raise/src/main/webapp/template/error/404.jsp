<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    <meta charset="UTF-8">
    <title>Raise - 404</title>
</head>
<body>
<div class="page">
    <div class="header-logo section centered">
        <a href="/api/home">
            <h3>Raise</h3>
        </a>
    </div>
    <main class="main centered">
        <div class="centered error-msg">
            <h2 class="error-code">404</h2>
            <h2>Page not found</h2>
            <a class="btn" href="/api/home">Вернуться на главную</a>
        </div>
    </main>
    <footer>
        <div class="page-footer centered">
            <div>
                <h4>
                    © 2020 Copyright: Ilya Buglakov
                </h4>
            </div>
        </div>
    </footer>
</div>
</body>
</html>