function Planet(radius, orbit, speed, color, name) {

    this.color = color;
    this.radius = radius;
    this.orbit = orbit;
    this.speed = speed;
    this.name = name;
    this.id = -1;

    this.angle = 0;
    this.xCurrent = 0;
    this.yCurrent = 0;

    this.move = function (x, y) {

        if (this.angle == 360) {
            this.angle = 0;
        }
        this.angle += this.speed / 1000;
        this.xCurrent = x + Math.sin(this.angle) * this.orbit;
        this.yCurrent = y + Math.cos(this.angle) * this.orbit;
    };

    this.getX = function () {
        return this.xCurrent;
    };

    this.getY = function () {
        return this.yCurrent;
    };

    this.getRadius = function () {
        return this.radius;
    };

    this.getColor = function () {
        return this.color;
    };

    this.getName = function () {
        return this.name;
    };

    this.getOrbit = function () {
        return this.orbit;
    };

    this.getSpeed = function () {
        return this.speed;
    };

    this.setRadius = function (radius) {
        this.radius = radius;
    };

    this.setColor = function (color) {
        this.color = color;
    };

    this.setName = function (name) {
        this.name = name;
    };

    this.setOrbit = function (orbit) {
        this.orbit = orbit;
    };

    this.setSpeed = function (speed) {
        this.speed = speed;
    };

    this.setId = function (id) {
        this.id = id;
    };

    this.getId = function () {
        return this.id;
    }
}

function Drawer(canvas, planets, x, y) {

    this.planets = planets;
    this.context = canvas.getContext("2d");

    this.x = x;
    this.y = y;

    this.setXY = function (x, y) {
        this.x = x;
        this.y = y;
    };

    this.move = function (planet, x, y) {
        planet.move(x, y);
    };

    this.begin = function () {

        this.context.beginPath();
    };

    this.end = function () {
        this.context.closePath();
    };

    this.draw = function (planet) {

        this.begin();
        this.context.arc(planet.getX(), planet.getY(), planet.getRadius(), 0, 2 * Math.PI);
        this.context.fillStyle = planet.getColor();
        this.context.fill();
    };

    this.go = function () {

        for (var i = 0; i < this.planets.length; i++) {

            this.move(this.planets[i], this.x, this.y);
        }

        this.context.clearRect(0, 0, canvas.width, canvas.height);
        this.context.fillStyle = "#666666";
        this.context.fillRect(0, 0, canvas.width, canvas.height);

        for (i = 0; i < this.planets.length; i++) {
            this.draw(this.planets[i]);
        }

        this.end();
    };

    var interval;

    this.start = function () {
        var drawer = this;
        interval = setInterval(function () {
                drawer.go();
            }, 10
        );
    };

    this.stop = function () {
        clearInterval(interval);
    };

    this.delete = function (name) {
        for (var i = 0; i < this.planets.length; i++) {

            if (this.planets[i].getName() == name) {
                var planet = this.planets[i];
                this.planets.splice(i, 1);

                drawer.stop();
                drawer.start();

                return planet;
            }
        }
    };

    this.edit = function (oldName, newPlanet) {
        var oldPlanet = this.has(oldName);

        if (oldPlanet == null) {
            console.error("Error, no such situation expected.");
            return null;
        } else {
            oldPlanet.setColor(newPlanet.getColor());
            oldPlanet.setOrbit(newPlanet.getOrbit());
            oldPlanet.setName(newPlanet.getName());
            oldPlanet.setRadius(newPlanet.getRadius());
            oldPlanet.setSpeed(newPlanet.getSpeed());
            oldPlanet.setId(oldPlanet.getId());
            return oldPlanet;
        }
    };

    this.add = function (planet) {
        this.planets.push(planet);
    };

    this.has = function (name) {
        for (var i = 0; i < this.planets.length; i++) {
            if (name == planets[i].getName()) {
                return planets[i];
            }
        }
        return null;
    };
}

var canvas = document.getElementById("planetsCanvas");
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

var timeout;
$(window).resize(function () {
    clearTimeout(timeout);
    timeout = setTimeout(function () {
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
        drawer.setXY(window.innerWidth / 2, window.innerHeight / 2);
    }, 500);
});

var sun = new Planet(40, 0, 0, "yellow", "Солнце");
var planets = [sun];
var drawer = new Drawer(canvas, planets, canvas.width / 2, canvas.height / 2);
