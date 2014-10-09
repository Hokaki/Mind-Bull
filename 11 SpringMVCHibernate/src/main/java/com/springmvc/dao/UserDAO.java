/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springmvc.model.User;
import java.util.List;

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
        User userToUpdate = getUser(user.getUserId());
        userToUpdate.setName(user.getName());
        userToUpdate.setStreetAddress(user.getStreetAddress());
        userToUpdate.setHouseNumber(user.getHouseNumber());
        userToUpdate.setCity(user.getCity());
        userToUpdate.setRole(user.getRole());
        
        getCurrentSession().update(userToUpdate);

    }

    public User getUser(int id) {
        User user = (User) getCurrentSession().get(User.class, id);
        return user;
    }

    public void deleteUser(int id) {
        User user = getUser(id);
        if (user != null) {
            getCurrentSession().delete(user);
        }
    }
    
    public void deleteRole(int id) {
        User userToUpdate = getUser(id);
        userToUpdate.setRole(null);
        getCurrentSession().update(userToUpdate);
            
           
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return getCurrentSession().createQuery("from User").list();
    }

    public  void storeAllUsers(List<User> users) {
             
        for (User user : users) {
            getCurrentSession().save(user);
        }
       
    } 
}
