<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Планеты - авторизация</title>

    <!-- Styles -->
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/style.css">

    <!-- Scripts -->
    <script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="resources/js/authorization.js"></script>

</head>
<body>
<div class="all-content-wrapper">
    <div class="authorization-panel">
        <form method="post" action="/j_spring_security_check" class="form-inline">
            <div class="form-group">
                <label for="email">Почта</label>
                <input name="username" id="email" class="user-data form-control"/>
            </div>

            <div class="form-group">
                <label for="password">Пароль</label>
                <input name="password" type="password" id="password" class="user-data form-control"/>
            </div>
            <button id="loginButton" type="button" class="btn btn-info">Войти</button>
            <button type="button" onclick="window.location='/registration';"
                    class="btn btn-info">Регистрация
            </button>

            <input type="hidden"
                   name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <script>
            function remove() {
                $("#error").fadeOut(4000);
                $("#message").fadeOut(4000);
            }
        </script>
        <c:if test="${error != null}">
            <div id="error" class="message-panel alert alert-danger" role="alert">${error}</div>
            <script>
                remove();
            </script>
        </c:if>

        <c:if test="${msg != null}">
            <div id="message" class="message-panel alert alert-success" role="alert">${msg}</div>
            <script>
                remove();
            </script>
        </c:if>
    </div>
</div>
</body>
</html>
