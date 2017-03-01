package com.gmail.vesinitsyn.planets.service;

import com.gmail.vesinitsyn.planets.model.Planet;

import java.util.List;

public interface PlanetServiceInterface {

    void addPlanet(Planet planet);
    List<Planet> findPlanets(Long userId);
    boolean deletePlanet(Planet planet);
}
