/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.model;

import java.io.Serializable;
import org.hibernate.annotations.Type;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Joost
 */
@Entity
public class Patient extends Person implements Serializable {
    
    private int bsn;
    private User user;

    public Patient() {
    }

    public Patient(int bsn, String firstName, String lastName, User user) {
        super(firstName, lastName);
        this.bsn = bsn;
        this.user = user;
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

    @ManyToOne
    @Type(type="encryptedString")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
