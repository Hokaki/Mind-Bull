package com.ewa.flamingraven.model;



import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

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
    @Column(name = "totalTime")
    private int totalTime;
    @Column(name = "avgTime")
    private int avgTime;
    @Column(name = "minTime")
    private int minTime;
    @Column(name = "maxTime")
    private int maxTime;
    @Column(name = "maxSpeed")
    private int maxSpeed;

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(int avgTime) {
        this.avgTime = avgTime;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}
