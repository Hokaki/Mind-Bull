/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.firefalcon.model.User;
//import com.sun.xml.internal.bind.v2.model.core.ID;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mohamed
 */
@Repository
public class UserDAO {

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
     //   User user = (User) getCurrentSession().get(User.class, user.);
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

    public List<User> getResultList(String userName, String password) {
        Criteria cr = getCurrentSession().createCriteria(User.class);

        Criterion name = Restrictions.eq("username", userName);
        Criterion pass = Restrictions.eq("password", password);

        LogicalExpression eqExp = Restrictions.and(name, pass);
        cr.add(eqExp);

        List rs = cr.list();

        return rs;
    }
}
