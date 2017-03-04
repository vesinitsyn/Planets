var addButton = $("#addPlanetButton");
var editButton = $("#editPlanetButton");
var tableWrapper = $("#planetsTableWrapper");
var planetsTable = $("#planetsTable");

var nameField = $("#planetName");
var radiusField = $("#planetRadius");
var orbitField = $("#planetOrbit");
var speedField = $("#planetSpeed");
var colorField = $("#planetColor");

var editableName;

function addTableRow(planetName) {
    planetsTable.append("<tr id='" + planetName + "Row' class='planet-table-row'>" +
        "<td>" + planetName + "</td>" +
        "<td>" +
        "<button id='" + planetName + "Edit" + "' type='button' class='btn btn-default'><span " +
        "class='glyphicon glyphicon-cog' aria-hidden='true'></span></button>" +
        "</td>" +
        "<td>" +
        "<button id='" + planetName + "Button" + "'type='button' class='btn btn-default' name='"
        + planetName + "'><span class='glyphicon glyphicon-trash' aria-hidden='true'></span></button>" +
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

    var inputName = input.attr("id");
    var name = input.val();
    var element = $("#" + inputName + "Group");
    if (name === "") {
        element.addClass("has-error");
        element.attr("data-toggle", "tooltip");
        element.attr("data-placement", "right");
        element.attr("title", "Название не должно быть пустым");
        element.tooltip('show');
    }
    else {
        element.removeClass("has-error");
        element.tooltip("destroy");
    }

    return name;
}
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

function sendPOST(url, planet, f) {
    $.ajax({
            method: "POST",
            beforeSend: function (request) {
                request.setRequestHeader(header, token);
            },
            url: url,
            data: planet,
            success: function (a) {
                f(a);
            }
        }
    );
}

function sendToServer(planet) {
    sendPOST("add", planet, function (a) {
        planet.setId(parseInt(a));
    });
}

function deleteOnServer(planet) {
    sendPOST("delete", planet, function (a) {
    });
}

function updateOnServer(planet) {
    console.log(planet);
    sendPOST("update", planet, function (a) {
    });
}

function planetExists(input, name) {
    var element = $("#" + input.attr("id") + "Group");

    if (drawer.has(name) != null) {
        element.addClass("has-error");
        element.attr("data-toggle", "tooltip");
        element.attr("data-placement", "right");
        element.attr("title", "Планета с данным именем уже существует");
        element.tooltip("show");
        return true;
    } else {
        element.removeClass("has-error");
        element.tooltip("destroy");
        return false;
    }
}


function addPlanet(newPlanet) {

    console.log("add");
    drawer.add(newPlanet);
    drawer.stop();
    drawer.start();

    if (tableWrapper.css("visibility") == "hidden") {
        tableWrapper.css("visibility", "visible");
    }

    var planetName = newPlanet.getName();

    addTableRow(planetName);

    $("#" + planetName + "Button").click(function () {
        deleteTableRow(this);
        deleteOnServer(drawer.delete(planetName));
    });

    $("#" + planetName + "Edit").click(function () {

        console.log($(this).attr("id"));
        switchButtons(true, false);

        var name = $(this).attr("id");
        name = name.substring(0, name.length - 4);
        editableName = name;

        var planet = drawer.has(name);
        nameField.val(planet.getName());
        radiusField.val(planet.getRadius());
        orbitField.val(planet.getOrbit());
        speedField.val(planet.getSpeed());
        colorField.val(planet.getColor());
    });
}

function getPlanet() {
    var planetName = getName(nameField);
    var planetRadius = getNumericValue(radiusField);
    var planetOrbit = getNumericValue(orbitField);
    var planetSpeed = getNumericValue(speedField);
    var planetColor = colorField.val();

    planetRadius = parseFloat(planetRadius);
    planetOrbit = parseFloat(planetOrbit);
    planetSpeed = parseFloat(planetSpeed);

    return new Planet(planetRadius, planetOrbit, planetSpeed, planetColor, planetName);
}

function emptyInputs() {
    nameField.val("");
    radiusField.val("");
    orbitField.val("");
    speedField.val("");
    colorField.val("#000000");
}

function switchButtons(add, edit) {
    addButton.prop("disabled", add);
    editButton.prop("disabled", edit);
}

function addPlanetOnClick() {

    var planet = getPlanet();

    if ($(".has-error").length == 0) {
        if (!planetExists(nameField, planet.getName())) {
            addPlanet(planet);
            sendToServer(planet);
            emptyInputs();
        }
    }
}

function editPlanetOnClick() {
    var planet = getPlanet();

    if ($(".has-error").length == 0) {
        planet = drawer.edit(editableName, planet);

        if (planet != null) {
            updateOnServer(planet);
        }
        switchButtons(false, true);
        emptyInputs();

        $("#" + editableName + "Edit").attr("id", planet.getName() + "Edit");
        $("#" + editableName + "Row").attr("id", planet.getName() + "Row");
        $("#" + editableName + "Button").attr("id", planet.getName() + "Button");
        $("#" + planet.getName() + "Row").find("td:first").html(planet.getName());
    }
}
addButton.click(addPlanetOnClick);
editButton.click(editPlanetOnClick);
