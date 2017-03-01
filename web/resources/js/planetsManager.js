var addButton = $("#addPlanetButton");
var tableWrapper = $("#planetsTableWrapper");
var planetsTable = $("#planetsTable");

var nameField = $("#planetName");
var radiusField = $("#planetRadius");
var orbitField = $("#planetOrbit");
var speedField = $("#planetSpeed");
var colorField = $("#planetColor");

function addTableRow(planetName, planetId) {
    planetsTable.append("<tr id='" + planetName + "Row' class='planet-table-row'>" +
        "<td>" + planetName + "</td>" +
        "<td>" +
        "<button id='" + planetId + "'type='button' class='btn btn-default' name='"
        + planetName + "'>Удалить</button>" +
        "</td>" +
        "</tr>");
}

function deleteTableRow(button) {
    var planetName = $(button).attr("name");

    $("#" + planetName + "Row").remove();

    if ($(".planet-table-row").length == 0) {
        tableWrapper.css("visibility", "hidden");
    }
}

function getNumericValue(input) {
    var value = parseFloat(input.val());
    var name = input.attr("id");
    var element = $("#" + name + "Group");
    if (isNaN(value) || value <= 0) {
        element.addClass("has-error");

        element.attr("data-toggle", "tooltip");
        element.attr("data-placement", "right");
        element.attr("title", "Введите положительное число");
        element.tooltip('show');
        return -1;
    }
    else {
        element.removeClass("has-error");
        element.tooltip("destroy");
        return value;
    }
}

function getName(input) {
    var name = input.val();
    var element = $("#" + input.attr("id") + "Group");

    if (drawer.has(name)) {
        element.addClass("has-error");
        element.attr("data-toggle", "tooltip");
        element.attr("data-placement", "right");
        element.attr("title", "Планета с данным именем уже существует");
        element.tooltip("show");
    } else {
        element.removeClass("has-error");
        element.tooltip("destroy");
        return name;
    }
}
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

function sendToServer(planet) {


    $.ajax({
        method: "POST",
        beforeSend: function (request) {
            request.setRequestHeader(header, token);
        },
        url: "add",
        data: planet
    });
}

function deleteOnServer(planet) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        method: "POST",
        beforeSend: function (request) {
            request.setRequestHeader(header, token);
        },
        url: "delete",
        data: planet
    });
}

function addPlanet(planetName, planetRadius, planetOrbit, planetSpeed, planetColor) {

    planetRadius = parseFloat(planetRadius);
    planetOrbit = parseFloat(planetOrbit);
    planetSpeed = parseFloat(planetSpeed);

    var newPlanet = new Planet(planetRadius, planetOrbit, planetSpeed, planetColor, planetName);

    drawer.add(newPlanet);
    drawer.stop();
    drawer.start();

    if (tableWrapper.css("visibility") == "hidden") {
        tableWrapper.css("visibility", "visible");
    }

    var planetId = planetName + "Button";

    addTableRow(planetName, planetId);

    $("#" + planetId).click(function () {
        deleteTableRow(this);
        deleteOnServer(drawer.delete(planetName));
    });

    return newPlanet;
}

function addPlanetOnClick() {
    var planetName = getName(nameField);
    var planetRadius = getNumericValue(radiusField);
    var planetOrbit = getNumericValue(orbitField);
    var planetSpeed = getNumericValue(speedField);
    var planetColor = colorField.val();

    if ($(".has-error").length == 0) {
        console.log("no error");
        var planet = addPlanet(planetName, planetRadius, planetOrbit, planetSpeed, planetColor);
        sendToServer(planet);
    }
}

addButton.click(addPlanetOnClick);
