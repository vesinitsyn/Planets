package com.gmail.vesinitsyn.planets.dao;

import com.gmail.vesinitsyn.planets.model.User;

public interface UserDAOInterface {

    void insertUser(User user);

    User findUser(String email);
}
