/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.model;

//import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author chrisvanderheijden
 */
@Entity
public class Affliction {
    
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "sideNote")
    private String sideNote;
    @Column(name = "BSN")
    private int BSN;

    
    
    public Affliction(int id, String description, String sideNote, int BSN) {
      
     
        this.id = id;
        this.description = description;
        this.sideNote = sideNote;
        this.BSN = BSN;
       
    }
    
    public Affliction(){
    
    }

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

    public int getBSN() {
        return BSN;
    }

    public void setBSN(int BSN) {
        this.BSN = BSN;
    }
    
    
    
    
    
}