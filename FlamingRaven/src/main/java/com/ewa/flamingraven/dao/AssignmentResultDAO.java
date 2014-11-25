package com.ewa.flamingraven.dao;

import com.ewa.flamingraven.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Joost on 11/18/2014.
 */

@Repository
public class AssignmentResultDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addUser(User user) {
        getCurrentSession().save(user);
    }

    public void updateUser(User user) {
        User userToUpdate = getUser(user.getUsername());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());

        getCurrentSession().update(userToUpdate);

    }

    public User getUser(String userName) {
        User user = (User) getCurrentSession().get(User.class, userName);
        return user;
    }

    public void deleteUser(String userName) {
        User user = getUser(userName);
        if (user != null) {
            getCurrentSession().delete(user);
        }
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return getCurrentSession().createQuery("from User").list();
    }

    public void storeAllUsers(List<User> users) {

        for (User user : users) {
            getCurrentSession().save(user);
        }

    }
}
