package com.gmail.vesinitsyn.planets.dao;

import com.gmail.vesinitsyn.planets.model.Planet;

import java.util.List;

public interface PlanetDAOInterface {

    void insertPlanet(Planet planet);

    void deletePlanet(Planet planet);

    List<Planet> selectPlanets(Long userId);

    void update(Planet planet);
}
