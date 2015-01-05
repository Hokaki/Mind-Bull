package com.ewa.flamingraven.dao;

import com.ewa.flamingraven.connectivity.DbConnectionSQL;
import com.ewa.flamingraven.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ewa.flamingraven.model.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

/**
 * Created by Joost on 11/18/2014.
 */

@Repository
public class AssignmentResultDAO {
    @Autowired
    DbConnectionSQL conn = new DbConnectionSQL();

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

    public void add(AssignmentResult assignmentResult) throws SQLException {
        PreparedStatement prdstmt = null;
        String query = "INSERT INTO `assignmentresult` (id, "
                + "avgTime, date, maxSpeed, maxTime, minTime, repetitions, totalTime, exercise_id)"
                + "Values(?,?,?,?,?,?,?,?,?)";

        conn.startConnection();
        prdstmt = conn.getConnection().prepareStatement(query);
        prdstmt.setInt(1, assignmentResult.getId());
        prdstmt.setInt(2, assignmentResult.getAvgTime());
        prdstmt.setString(3, assignmentResult.getDate());
        prdstmt.setInt(4, assignmentResult.getMaxSpeed());
        prdstmt.setInt(5, assignmentResult.getMaxTime());
        prdstmt.setInt(6, assignmentResult.getMinTime());
        prdstmt.setInt(7, assignmentResult.getRepetitions());
        prdstmt.setInt(8, assignmentResult.getTotalTime());
        prdstmt.setInt(9, 1);


        prdstmt.executeUpdate();

        if (conn != null) {
            conn.closeConnection();
        }
    }
}



