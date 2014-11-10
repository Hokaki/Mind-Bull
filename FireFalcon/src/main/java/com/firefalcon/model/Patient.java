/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Joost
 */
@Entity
public class Patient extends Person implements Serializable {

    @Id
    @Column(name = "bsn")
    @Type(type = "encryptedIntegerAsString")
    private int BSN;

    @OneToMany(mappedBy = "patient")
    private List<Overview> overviews;
    
    public Patient() {
    }

    public Patient(int BSN, String firstName, String lastName) {
        super(firstName, lastName);
        this.BSN = BSN;
        overviews = new ArrayList<Overview>();
    }

    
    public int getBSN() {
        return BSN;
    }

    public void setBSN(int BSN) {
        this.BSN = BSN;
    }

    public List<Overview> getOverviews() {
        return overviews;
    }

    public void setOverviews(List<Overview> overviews) {
        this.overviews = overviews;
    }
    
    

}
