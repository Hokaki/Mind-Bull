/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.firefalcon.dao.UserDAO;
import com.firefalcon.model.User;

/**
 *
 * @author Mohamed
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public User getUser(String userName) {
        return userDAO.getUser(userName);
    }

    public void deleteUser(String userName) {
        userDAO.deleteUser(userName);
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public void storeAllUsers(List<User> users) {

        userDAO.storeAllUsers(users);

    }

    public int checkRow(User user) {
        System.out.println(user.getUsername() + user.getPassword());

        List userList = userDAO.getResultList(user.getUsername(), user.getPassword());
        int numRow = userList.size();
        return numRow;
    }
}
