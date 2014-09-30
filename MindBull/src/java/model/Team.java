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
public class Team implements Serializable {

    @GeneratedValue
    @Id
    private long id;
    private String name;
    private String yell;

    @OneToMany(mappedBy = "team")
    private List<Member> members;

    public Team() {
        super();
    }

    public Team(String name, String yell) {
        super();
        this.name = name;
        this.yell = yell;
        members = new ArrayList<Member>();
    }

    public void addMember(Member m) {
        if (members.size() < 4) {
            members.add(m);
            m.setTeam(this);
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYell() {
        return yell;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYell(String yell) {
        this.yell = yell;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

}
