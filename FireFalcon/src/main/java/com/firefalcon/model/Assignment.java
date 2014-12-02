/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.model;

import com.dhtmlx.planner.DHXEvent;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Type;

/**
 *
 * @author Hokaki
 */

@Entity
public class Assignment implements Serializable{
    
    private int idAssignment;
    private Patient bsn;
    private Exercise exerciseId;
    private String repetitions;
    private String start_date;
    private String end_date;
 
    
    public Assignment(Exercise exerciseId, Patient bsn, String repetitions, String start_date, String end_date){
        
        this.bsn = bsn;
        this.exerciseId = exerciseId;
        this.repetitions = repetitions;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    
    public Assignment(){
        
    }
    

    @Id
    @GeneratedValue  
    public int getIdAssignment() {
        return idAssignment;
    }

    public void setIdAssignment(int idAssignment) {
        this.idAssignment = idAssignment;
    }

    @ManyToOne
    @JoinColumn(name = "bsn")
    @Type(type = "encryptedIntegerAsString")
    public Patient getBsn() {
        return bsn;
    }

    public void setBsn(Patient bsn) {
        this.bsn = bsn;
    }

   @ManyToOne
   @JoinColumn(name = "id")
   public Exercise getExerciseId() {
       return exerciseId;
   }

   public void setExerciseId(Exercise exerciseId) {
       this.exerciseId = exerciseId;
   }

    public String getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(String repetitions) {
        this.repetitions = repetitions;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
   
   
   
}
