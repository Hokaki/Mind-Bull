/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.model;

//import java.io.Serializable;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Type;

/**
 *
 * @author chrisvanderheijden
 */
@Entity
public class Affliction implements Serializable{
    
    private int id;
    
    @Column(name = "description")
    private String description;
    @Column(name = "sideNote")
    private String sideNote;
    private Patient bsn;

    
    
    public Affliction(String description, String sideNote, Patient bsn) {
      
        this.description = description;
        this.sideNote = sideNote;
        this.bsn = bsn;
       
    }
    
    public Affliction(){
    
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideNote() {
        return sideNote;
    }

    public void setSideNote(String sideNote) {
        this.sideNote = sideNote;
    }
    
    @ManyToOne
    @JoinColumn(name = "bsn")
    //@Type(type = "encryptedIntegerAsString")
    public Patient getBsn() {
        return bsn;
    }

    public void setBsn(Patient bsn) {
        this.bsn = bsn;
    }

}