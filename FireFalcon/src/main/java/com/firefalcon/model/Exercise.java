package com.firefalcon.model;

import java.io.Serializable;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Jeff
 */
@Entity
public class Exercise implements Serializable{

    @GeneratedValue
    @Id
    @Column(name = "id")
    private int id;
    private String name;
    private String description;

    public Exercise() {
    }
    public Exercise(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    
    public int getId() {
        return id;
    }

    @Type(type= "encryptedString")
    public String getName() {
        return name;
    }

    @Type(type= "encryptedString")
    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
