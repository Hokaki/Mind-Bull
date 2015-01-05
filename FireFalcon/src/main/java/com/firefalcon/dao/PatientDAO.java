package com.firefalcon.dao;

import com.firefalcon.model.AssignmentResult;
import com.firefalcon.model.Patient;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;
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
        Patient patientToUpdate = getPatient(patient.getBsn());
        patientToUpdate.setFirstName(patient.getFirstName());
        patientToUpdate.setLastName(patient.getLastName());
        patientToUpdate.setBsn(patient.getBsn());
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
    
    @SuppressWarnings("unchecked")
    public List<Patient> getPatientsByUser(String username) {
        return getCurrentSession().createQuery("From Patient WHERE user = '" + username +"'").list();
    }

    @SuppressWarnings("unchecked")
    public List<AssignmentResult> getResults(int bsn) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword("FireFalcon");
        encryptor.setSaltGenerator(new ZeroSaltGenerator());
        return getCurrentSession().createQuery("from AssignmentResult where patient_bsn = '" + encryptor.encrypt(String.valueOf(bsn)) + "' order by date ASC").list();
    }

    public void storeAllPatients(List<Patient> patients) {

        for (Patient patient : patients) {
            getCurrentSession().save(patient);
        }

    }
}
