/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.dao;

import com.firefalcon.model.Exercise;
import com.firefalcon.model.Overview;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hokaki
 */
@Repository
public class OverviewDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public void addOverviewBSN(int patientBSN){
        getCurrentSession().save(patientBSN);
    }

    public void addOverview(Overview overview) {
        getCurrentSession().save(overview);
    }

    public void updateOverview(Overview overview) {
        Overview overviewToUpdate = getOverview(overview.getPatientBSN());
        overviewToUpdate.setPatientBSN(overview.getPatientBSN());
        overviewToUpdate.setId(overview.getId());
        overviewToUpdate.setName(overview.getName());
        overviewToUpdate.setExercise(overview.getExercise());
        getCurrentSession().update(overviewToUpdate);
    }
    
    public Overview getOverview(int patientBSN) {
        Overview overview = (Overview) getCurrentSession().get(Overview.class, patientBSN);
        return overview;
    }
    
       public void deleteOverview(int patientBSN) {
        Overview overview = getOverview(patientBSN);
        if (overview != null) {
            getCurrentSession().delete(overview);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Overview> getOverviews() {
        return getCurrentSession().createQuery("from Overview").list();
    }

    public void storeAllOverviews(List<Overview> overviews) {

        for (Overview overview : overviews) {
            getCurrentSession().save(overview);
        }

    }
}
    

