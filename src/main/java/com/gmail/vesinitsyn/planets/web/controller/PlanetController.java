package com.gmail.vesinitsyn.planets.web.controller;

import com.gmail.vesinitsyn.planets.model.Planet;
import com.gmail.vesinitsyn.planets.model.User;
import com.gmail.vesinitsyn.planets.service.PlanetServiceInterface;
import com.gmail.vesinitsyn.planets.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanetController {

    @Autowired
    private PlanetServiceInterface planetService;

    @Autowired
    private UserServiceInterface userService;


    @RequestMapping("/add")
    public void add(Planet planet) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        planet.setUser(userService.findUser(userName));

        planetService.addPlanet(planet);
    }

    @RequestMapping("/delete")
    public void delete(Planet planet) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        planet.setUser(userService.findUser(userName));

        planetService.deletePlanet(planet);
    }
}
