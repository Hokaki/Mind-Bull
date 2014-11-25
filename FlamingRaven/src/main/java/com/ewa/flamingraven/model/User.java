package com.ewa.flamingraven.model;


        import java.io.Serializable;
        import org.hibernate.annotations.Type;

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

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String firstName, String lastName) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
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

}
