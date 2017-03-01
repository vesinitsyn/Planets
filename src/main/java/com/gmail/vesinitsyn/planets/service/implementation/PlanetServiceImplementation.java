package com.gmail.vesinitsyn.planets.service.implementation;

import com.gmail.vesinitsyn.planets.dao.PlanetDAOInterface;
import com.gmail.vesinitsyn.planets.model.Planet;
import com.gmail.vesinitsyn.planets.service.PlanetServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetServiceImplementation implements PlanetServiceInterface {

    @Autowired
    private PlanetDAOInterface planetDAO;

    public void addPlanet(Planet planet) {
        planetDAO.insertPlanet(planet);
    }

    public List<Planet> findPlanets(Long userId) {
        return null;
    }

    public boolean deletePlanet(Planet planet) {
        planetDAO.deletePlanet(planet);
        return true;
    }
}
