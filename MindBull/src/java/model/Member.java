/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author huub
 */

@Entity(name="ClubMember")
public class Member implements Serializable {
  
  @GeneratedValue
  @Id
  private long id;
  private String firstName = null;
  private String lastName = null;
  
  @ManyToOne
  private Team team;
  
  public Member() {
    super();
  }
  
  public Member(String firstName, String lastName, Team team) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.team = team;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Team getTeam() {
    return team;
  }
  
  public long getId() {
    return id;
  }
}
