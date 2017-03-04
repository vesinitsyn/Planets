<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<html>
<head>
    <title>Планеты - главная</title>

    <!-- Styles -->
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/style.css">

    <!-- Scripts -->
    <script src="resources/js/jquery-3.1.1.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="all-content-wrapper canvas-wrapper">
    <canvas id='planetsCanvas' style="height: auto;"></canvas>
    <div class="add-planet-panel well">
        <form>
            <div id="planetNameGroup" class="form-group">
                <label for="planetName">Название</label>
                <input id="planetName" class="form-control">

            </div>
            <div id="planetRadiusGroup" class="form-group">
                <label for="planetRadius">Радиус планеты</label>
                <input id="planetRadius" class="form-control">
            </div>
            <div id="planetOrbitGroup" class="form-group">
                <label for="planetOrbit">Радиус орбиты</label>
                <input id="planetOrbit" class="form-control">
            </div>
            <div id="planetSpeedGroup" class="form-group">
                <label for="planetSpeed">Скорость</label>
                <input id="planetSpeed" class="form-control">
            </div>
            <div id="planetColorGroup" class="form-group">
                <label for="planetColor">Цвет</label>
                <input type="color" id="planetColor" class="form-control">
            </div>
            <button id="addPlanetButton" type="button" class="add-planet-button btn btn-default"
            >Создать
            </button>
            <button id="editPlanetButton" type="button" disabled
                    class="add-planet-button btn btn-default"
            >Сохранить
            </button>
        </form>
    </div>

    <div class="planets-exit-panel">
        <div class="user-info-table-pane well">
            <table class="table table-striped">
                <tr>
                    <th>Имя</th>
                    <th>Почта</th>
                </tr>
                <tr>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                </tr>
            </table>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <button class="btn btn-default" onclick="formSubmit()">Выход</button>
            </c:if>
        </div>
        <div id="planetsTableWrapper" class="planets-table-panel well">
            <table id="planetsTable" class="table table-bordered">
                <tr>
                    <th>Название</th>
                </tr>
            </table>
        </div>
    </div>

    <c:url value="/j_spring_security_logout" var="logoutUrl"/>

    <!-- csrf support -->
    <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>
</div>

</body>
<script src="resources/js/planets.js" type="text/javascript"></script>
<script src="resources/js/planetsManager.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        drawer.start();
        var planet;
        <c:forEach var="planet" items="${planets}">
        planet = new Planet(<c:out value="${planet.radius}"/>, <c:out value="${planet.orbit}"/>,
                <c:out value="${planet.speed}"/>,
                '<c:out value="${planet.color}"/>',
                '<c:out value="${planet.name}"/>');
        planet.setId(<c:out value="${planet.id}"/>);

        addPlanet(planet);
        </c:forEach>
    })
    ;
</script>
</html>