/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.services;

import com.firefalcon.dao.AfflictionDAO;
import com.firefalcon.model.Affliction;
import com.firefalcon.model.Patient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chrisvanderheijden
 */
@Service
@Transactional
public class AfflictionService {

    @Autowired
    private AfflictionDAO afflictionDAO;

    public void addAffliction(Affliction affliction) {
        afflictionDAO.addAffliction(affliction);
    }

    public void updateAffliction(Affliction affliction) {
        afflictionDAO.updateAffliction(affliction);

    }

    public Affliction getAffliction(int bsn) {
        return afflictionDAO.getAffliction(bsn);
    }

    public void deleteAffliction(int bsn) {
        afflictionDAO.deleteAffliction(bsn);
    }

    public List<Affliction> getAfflictions() {
        return afflictionDAO.getAfflictions();
    }

    public List<Affliction> getAfflictionsByPatients(String bsn) {
        return afflictionDAO.getAfflictionsByPatients(bsn);
    }

    public void storeAllAfflictions(List<Affliction> afflictions) {

        afflictionDAO.storeAllAfflictions(afflictions);

    }

}
