package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity(name="ClubMember")
public class Member implements Serializable {
  
  @GeneratedValue
  @Id
  private long id;
  private String firstName = null;
  private String lastName = null;
  
  @ManyToOne
  private Excercise excercise;
  
  public Member() {
    super();
  }
  
  public Member(String firstName, String lastName, Excercise excercise) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.excercise = excercise;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setExcercise(Excercise excercise) {
    this.excercise = excercise;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Excercise getExcercise() {
    return excercise;
  }
  
  public long getId() {
    return id;
  }
}
