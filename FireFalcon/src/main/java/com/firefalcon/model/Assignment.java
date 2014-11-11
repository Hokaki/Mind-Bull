/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Type;

/**
 *
 * @author Hokaki
 */

@Entity
public class Assignment implements Serializable{

    private int id;
    
    @Column(name = "name")
    private String name;
    @Column(name = "exercise")
    private String exercise;
  
    private Patient bsn;
    
//    private Exercise description;
    
    public Assignment(String name, String exercise, Patient bsn){
        
        this.name = name;
        this.exercise = exercise;
        this.bsn = bsn;
//        this.description = description;
    }
    
    public Assignment(){
    }

    @Id
    @GeneratedValue  
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

    @ManyToOne
    @JoinColumn(name = "bsn")
    public Patient getBsn() {
        return bsn;
    }

    public void setBsn(Patient bsn) {
        this.bsn = bsn;
    }

//    @ManyToMany
//    @JoinColumn(name = "description")
//    public Exercise getDescription() {
//        return description;
//    }
//
//    public void setDescription(Exercise description) {
//        this.description = description;
//    }
  
    
    
    
}