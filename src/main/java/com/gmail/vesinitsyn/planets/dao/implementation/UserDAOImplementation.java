package com.gmail.vesinitsyn.planets.dao.implementation;

import com.gmail.vesinitsyn.planets.dao.UserDAOInterface;
import com.gmail.vesinitsyn.planets.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDAOImplementation implements UserDAOInterface {

    @Resource
    private SessionFactory sessionFactory;

    public UserDAOImplementation() {
    }

    @Transactional
    public void insertUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Transactional
    public User findUser(String email) {


        String hql = "from User where email = :email";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("email", email);
        List<User> users = query.list();

        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }
}
