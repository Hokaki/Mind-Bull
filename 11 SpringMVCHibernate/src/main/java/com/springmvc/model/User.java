package com.springmvc.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String name;
    private String streetAddress;
    private String city;
    private String houseNumber;

    @ManyToOne
    private Role role;

    // constructors
    public User() {

    }

    public User(int userId, String name, String streetAddress, String city, String houseNumber, Role role) {

        this.setUserId(userId);
        this.setName(name);
        this.setStreetAddress(streetAddress);
        this.setCity(city);
        this.setHouseNumber(houseNumber);
        this.setRole(role);

    }


    /* Getters en setters voor de verschillende attributen van het Model */
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
