/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springmvc.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<User> users;

    // constructors   
    public Role() {

    }

    public Role(int roleId, String name) {

        this.setRoleId(roleId);
        this.setName(name);

    }

  // getters and setters     
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
