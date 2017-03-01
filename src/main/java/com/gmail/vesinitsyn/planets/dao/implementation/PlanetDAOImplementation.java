package com.gmail.vesinitsyn.planets.dao.implementation;

import com.gmail.vesinitsyn.planets.dao.PlanetDAOInterface;
import com.gmail.vesinitsyn.planets.dao.UserDAOInterface;
import com.gmail.vesinitsyn.planets.model.Planet;
import com.gmail.vesinitsyn.planets.model.User;
import com.gmail.vesinitsyn.planets.service.UserServiceInterface;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PlanetDAOImplementation implements PlanetDAOInterface {

    @Resource
    private SessionFactory sessionFactory;

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private UserDAOInterface userDAO;

    @Transactional
    public void insertPlanet(Planet planet) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        planet.setUser(userService.findUser(userName));
        sessionFactory.getCurrentSession().saveOrUpdate(planet);
    }

    @Transactional
    public void deletePlanet(Planet planet) {

        Session session = sessionFactory.getCurrentSession();

        String name = planet.getName();
        Long userId = planet.getUser().getId();

        Query query = session.createQuery("delete Planet where user_id = :id and " +
                "name = :name");
        query.setParameter("id", userId);
        query.setParameter("name", name);

        query.executeUpdate();
    }

    public List<Planet> selectPlanets(Long userId) {
        return null;
    }
}
