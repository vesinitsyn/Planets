package com.gmail.vesinitsyn.planets.service.implementation;


import com.gmail.vesinitsyn.planets.dao.UserDAOInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserDAOInterface userDAO;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.gmail.vesinitsyn.planets.model.User user = userDAO.findUser(email);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(com.gmail.vesinitsyn.planets.model.User user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getEmail(), user.getPassword(),
                user.getEnabled(), true, true, true, authorities);
    }
}
