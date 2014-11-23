/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.model;

import com.dhtmlx.planner.DHXEvent;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
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
public class Assignment extends DHXEvent implements Serializable{
    
    private int idAssignment;
    private Patient bsn;
    private Exercise exerciseId;
 
    
    public Assignment(Exercise exerciseId, Patient bsn){
        
        this.bsn = bsn;
        this.exerciseId = exerciseId;
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
   
}
