/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Type;

/**
 *
 * @author Hokaki
 */

@Entity
public class Overview {
    
    @Id
    @GeneratedValue  
    @Column(name = "patientBSN", unique = true, nullable = false)
    private int patientBSN;
    
    @Column(name = "id")
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "exercise")
    private String exercise;
    
    @ManyToOne
    private Patient patient;
    
    public Overview(int patientBSN, int id, String name, String exercise, Patient patient){
        this.patientBSN = patientBSN;
        this.id = id;
        this.name = name;
        this.exercise = exercise;
        this.patient = patient;
    }
    
    public Overview(int patientBSN){
        this.patientBSN = patientBSN;
    }
    
    public Overview(){
    }

    public int getPatientBSN(){
        return patientBSN;
    }
    
    public void setPatientBSN(int patientBSN){
        this.patientBSN = patientBSN;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    
}
