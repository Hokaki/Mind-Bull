/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.dao;

//import com.firefalcon.model.
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.firefalcon.model.Affliction;
import org.springframework.stereotype.Repository;




/**
 *
 * @author chrisvanderheijden
 */
@Repository
public class AfflictionDAO {
     
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addAffliction(Affliction affliction) {
        getCurrentSession().save(affliction);
    }

    public void updateAffliction(Affliction affliction) {
        Affliction afflictionToUpdate = getAffliction(affliction.getId());
        afflictionToUpdate.setSideNote(affliction.getSideNote());
        afflictionToUpdate.setDescription(affliction.getDescription());
//        exerciseToUpdate.setBSN(exercise.getBSN());
        getCurrentSession().update(afflictionToUpdate);
    }

    public Affliction getAffliction(int id) {
        Affliction affliction = (Affliction) getCurrentSession().get(Affliction.class, id);
        return affliction;
    }

    public void deleteAffliction(int id) {
        Affliction affliction = getAffliction(id);
        if (affliction != null) {
            getCurrentSession().delete(affliction);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Affliction> getAfflictions() {
        return getCurrentSession().createQuery("From Affliction").list();
    }

    public void storeAllAfflictions(List<Affliction> afflictions) {

        for (Affliction affliction : afflictions) {
            getCurrentSession().save(affliction);
        }

    }
   
}
