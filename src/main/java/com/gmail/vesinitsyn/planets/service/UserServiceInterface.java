package com.gmail.vesinitsyn.planets.service;

import com.gmail.vesinitsyn.planets.model.User;

public interface UserServiceInterface {

    void addUser(User user);

    User findUser(String email);
}
