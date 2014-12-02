package com.firefalcon.model;

import java.io.Serializable;
import org.hibernate.annotations.Type;
import javax.validation.constraints.*;
import javax.persistence.*;

/**
 *
 * @author Mohamed
 */
@Entity
public class User extends Person implements Serializable {

    @Id
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    @Column(name = "isAdmin")
    private boolean isAdmin;

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        
    }

    public User(String username, String password, String firstName, String lastName , boolean isAdmin) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User() {
    }

    @Type(type="encryptedString")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @Type(type="encryptedString")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    

}
