package com.hvl.feedApp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hvl.feedApp.Enums.Role;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table
public class Agent {

    @Id
    @SequenceGenerator(
            name = "agent_sequence",
            sequenceName = "agent_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agent_sequence")
    @Column(name="agent_id")
    private long agentID;

    // Relations
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    //@Transient
    //@JoinColumn(name = "owner_id")
    private List<Poll> ownedPolls;

/*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voter")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Vote> givenVotes;
*/


    // Attributes
    private String username;
    private String email;
    // TODO: implement hashed passwords!
    private String password;
/*

    public List<Vote> getGivenVotes() {
        return givenVotes;
    }
*/
/*

    public void setGivenVotes(List<Vote> givenVotes) {
        this.givenVotes = givenVotes;
    }
*/

    private Role role;

    // Constructors
    public Agent() {}

    public Agent(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Methods
    public void addOwnedPoll(Poll poll){
        ownedPolls.add(poll);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getAgentID() {
        return agentID;
    }

    public List<Poll> getOwnedPolls() {
        return ownedPolls;
    }

    public void setOwnedPolls(List<Poll> ownedPolls) {
        this.ownedPolls = ownedPolls;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
