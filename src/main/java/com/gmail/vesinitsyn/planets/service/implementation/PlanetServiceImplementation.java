package com.gmail.vesinitsyn.planets.service.implementation;

import com.gmail.vesinitsyn.planets.dao.PlanetDAOInterface;
import com.gmail.vesinitsyn.planets.dao.UserDAOInterface;
import com.gmail.vesinitsyn.planets.model.Planet;
import com.gmail.vesinitsyn.planets.service.PlanetServiceInterface;
import com.gmail.vesinitsyn.planets.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetServiceImplementation implements PlanetServiceInterface {

    @Autowired
    private PlanetDAOInterface planetDAO;

    @Autowired
    private UserDAOInterface userDAO;

    public void addPlanet(Planet planet) {
        setUser(planet);
        planetDAO.insertPlanet(planet);
    }

    public List<Planet> findPlanets(Long userId) {
        return null;
    }

    public boolean deletePlanet(Planet planet) {
        setUser(planet);
        planetDAO.deletePlanet(planet);
        return true;
    }

    public void updatePlanet(Planet planet) {
        setUser(planet);
        planetDAO.update(planet);
    }

    private void setUser(Planet planet) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        planet.setUser(userDAO.findUser(userName));
    }
}
