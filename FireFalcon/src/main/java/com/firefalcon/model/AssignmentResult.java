package com.firefalcon.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Jeff
 */
@Entity
public class AssignmentResult implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Exercise exercise;
    @Column(name = "date")
    private Date date;//YYYY-MM-DD
    @Column(name = "repetitions")
    private int repetitions;

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public String getDate() {
        String d = date.toString();
        return d;
    }

    public int getRepetitions() {
        return repetitions;
    }

}
