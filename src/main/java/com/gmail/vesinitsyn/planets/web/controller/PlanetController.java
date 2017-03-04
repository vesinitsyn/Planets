package com.gmail.vesinitsyn.planets.web.controller;

import com.gmail.vesinitsyn.planets.model.Planet;
import com.gmail.vesinitsyn.planets.model.User;
import com.gmail.vesinitsyn.planets.service.PlanetServiceInterface;
import com.gmail.vesinitsyn.planets.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PlanetController {

    @Autowired
    private PlanetServiceInterface planetService;

    @RequestMapping("/add")
    public String add(Planet planet) {

        planetService.addPlanet(planet);

        return Long.toString(planet.getId());
    }

    @RequestMapping("/delete")
    public void delete(Planet planet) {
        planetService.deletePlanet(planet);
    }

    @RequestMapping("/update")
    public void update(Planet planet) {
        planetService.updatePlanet(planet);
    }
}
