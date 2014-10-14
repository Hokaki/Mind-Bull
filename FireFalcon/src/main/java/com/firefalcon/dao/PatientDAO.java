package com.firefalcon.dao;

import com.firefalcon.model.Patient;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joost
 */
@Repository
public class PatientDAO {
   @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addPatient(Patient patient) {
        getCurrentSession().save(patient);
    }

    public void updatePatient(Patient patient) {
        Patient patientToUpdate = getPatient(patient.getBSN());
        patientToUpdate.setFirstName(patient.getFirstName());
        patientToUpdate.setLastName(patient.getLastName());
        patientToUpdate.setBSN(patient.getBSN());
        getCurrentSession().update(patientToUpdate);

    }

    public Patient getPatient(int bsn) {
        Patient patient = (Patient) getCurrentSession().get(Patient.class, bsn);
        return patient;
    }

    public void deletePatient(int bsn) {
        Patient patient = getPatient(bsn);
        if (patient != null) {
            getCurrentSession().delete(patient);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Patient> getPatients() {
        return getCurrentSession().createQuery("from Patient").list();
    }

    public  void storeAllUsers(List<Patient> patients) {
             
        for (Patient patient : patients) {
            getCurrentSession().save(patient);
        }
       
    } 
}