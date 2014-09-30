/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author huub
 */
@Entity
public class Excercise implements Serializable {

    @GeneratedValue
    @Id
    private long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "excercise")
    private List<Member> members;

    public Excercise() {
        super();
    }

    public Excercise(String name, String description) {
        super();
        this.name = name;
        this.description = description;
        members = new ArrayList<Member>();
    }

    public void addMember(Member m) {
        if (members.size() < 4) {
            members.add(m);
            m.setExcercise(this);
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

}
