package com.ewa.flamingraven.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Joost
 */
@Entity
public class ExerciseResult {

    private int id;

    public ExerciseResult(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
