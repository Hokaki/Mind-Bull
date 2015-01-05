/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.services;

import com.firefalcon.dao.PatientDAO;
import com.firefalcon.model.AssignmentResult;
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

    public void addPatient(Patient patient) {
        patientDAO.addPatient(patient);
    }

    public void updatePatient(Patient patient) {
        patientDAO.updatePatient(patient);
    }

    public Patient getPatient(int bsn) {
        return patientDAO.getPatient(bsn);
    }

    public void deletePatient(int bsn) {
        patientDAO.deletePatient(bsn);
    }

    public List<Patient> getPatients() {
        return patientDAO.getPatients();
    }
    
    public List<Patient> getPatientsByUser(String username) {
        return patientDAO.getPatientsByUser(username);
    }

    public List<AssignmentResult> getResults(int bsn) {
        return patientDAO.getResults(bsn);
    }

    public void storeAllPatient(List<Patient> users) {

        patientDAO.storeAllPatients(users);

    }
}
