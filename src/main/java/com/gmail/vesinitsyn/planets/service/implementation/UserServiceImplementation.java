package com.gmail.vesinitsyn.planets.service.implementation;

import com.gmail.vesinitsyn.planets.dao.UserDAOInterface;
import com.gmail.vesinitsyn.planets.model.User;
import com.gmail.vesinitsyn.planets.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserServiceInterface {

    @Autowired
    private UserDAOInterface userDAO;

    public void addUser(User user) {
        userDAO.insertUser(user);
    }

    public User findUser(String email) {
        User user = userDAO.findUser(email);
        return user;
    }
}
