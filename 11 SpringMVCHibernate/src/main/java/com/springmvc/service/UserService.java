package com.springmvc.service;

import com.springmvc.dao.UserDAO;
import com.springmvc.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }
     public void deleteRole(int id) {
        userDAO.deleteRole(id);
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public void storeAllUsers(List<User> users) {

        userDAO.storeAllUsers(users);

    }
}
