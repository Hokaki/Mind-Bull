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

/**
 *
 * @author Joost
 */
@Entity
public class Patient extends Person {
    @Id @GeneratedValue
    @Column(name = "bsn")
    private int BSN;

    public Patient(int BSN, String firstName, String lastName) {
        super(firstName, lastName);
        this.BSN = BSN;
    }

    public int getBSN() {
        return BSN;
    }

    public void setBSN(int BSN) {
        this.BSN = BSN;
    }

    
    
}
