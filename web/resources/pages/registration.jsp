<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Планеты - регистрация</title>
    <!-- Styles -->
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/style.css">

    <!-- Scripts -->
    <script src="resources/js/jquery-3.1.1.min.js" type="text/javascript"></script>
    <script src="resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="resources/js/registration.js" type="text/javascript"></script>
</head>
<body>
<div class="all-content-wrapper">
    <div class="registration-panel well">
        <form:form id="registrationForm" commandName="user" action="register" method="post">
            <div class="form-group">
                <label for="inputName">Имя</label>
                <form:input path="name" class="user-data form-control" id="inputName" placeholder="Имя"/>
            </div>
            <div class="form-group">
                <label for="inputEmail">Почта</label>
                <form:input path="email" type="email" class="user-data form-control" id="inputEmail"
                            placeholder="Почта"/>
            </div>
            <div class="form-group">
                <label for="inputPassword">Пароль</label>
                <form:password path="password" class="user-data form-control" id="inputPassword" placeholder="Пароль"/>
            </div>
            <div class="form-group">
                <label for="inputPasswordAgain">Повторите пароль</label>
                <input type="password" class="user-data form-control" id="inputPasswordAgain" placeholder="Пароль">
            </div>

            <form:errors path="*" cssClass="errorblock" element="div"/>

            <button id="registrationButton" type="button" class="btn btn-default">Регистрация</button>
        </form:form>
    </div>
</div>
</body>
</html>
