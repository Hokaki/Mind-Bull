/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.services;

import com.firefalcon.dao.OverviewDAO;
import com.firefalcon.model.Exercise;
import com.firefalcon.model.Overview;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hokaki
 */
@Service
@Transactional
public class OverviewService {

    @Autowired
    private OverviewDAO overviewDAO;
    
//    public void addOverviewBSN(int patientBSN){
//        overviewDAO.addOverviewBSN(patientBSN);
//    }

    public void addOverview(Overview overview) {
        overviewDAO.addOverview(overview);
    }

    public void updateOverview(Overview overview) {
        overviewDAO.updateOverview(overview);
    }

    public Overview getOverview(int patientBSN) {
        return overviewDAO.getOverview(patientBSN);
    }
    
    public void deleteOverview(int patientBSN){
        overviewDAO.deleteOverview(patientBSN);
    }

    public List<Overview> getOverviews() {
        return overviewDAO.getOverviews();
    }

    public void storeAllOverviews(List<Overview> overviews) {

        overviewDAO.storeAllOverviews(overviews);

    }

    
}
