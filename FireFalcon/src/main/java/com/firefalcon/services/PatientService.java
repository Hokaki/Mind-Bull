/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.services;

import com.firefalcon.dao.PatientDAO;
import com.firefalcon.model.Patient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Joost
 */
@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientDAO patientDAO;

    public void addUser(Patient patient) {
        patientDAO.addPatient(patient);
    }

    public void updateUser(Patient patient) {
        patientDAO.updatePatient(patient);
    }

    public Patient getPatient(int bsn) {
        return patientDAO.getPatient(bsn);
    }

    public void deleteUser(int bsn) {
        patientDAO.deletePatient(bsn);
    }

    public List<Patient> getPatients() {
        return patientDAO.getPatients();
    }

    public void storeAllUsers(List<Patient> users) {

        patientDAO.storeAllUsers(users);

    }
}
