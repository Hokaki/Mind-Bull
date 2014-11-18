/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ewa.flamingraven.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author Joost
 */
@Entity
public class Patient extends Person implements Serializable {
    
    private int bsn;

    public Patient() {
    }

    public Patient(int bsn, String firstName, String lastName) {
        super(firstName, lastName);
        this.bsn = bsn;
    }
    
    @Id
    @Column(name = "bsn")
    @Type(type = "encryptedIntegerAsString")
    public int getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

}
