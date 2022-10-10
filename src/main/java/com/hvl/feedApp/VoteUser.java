package com.hvl.feedApp;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class VoteUser {
    @Id
    @SequenceGenerator(
            name = "voteUser_sequence",
            sequenceName = "voteUser_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "voteUser_sequence")
    private long id;
    private String username;
    private String email;
    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "owner")
    private List<Poll> ownedPolls;

    public List<Poll> getOwnedPolls() {
        return ownedPolls;
    }

    public void addOwnedPoll(Poll poll){
        ownedPolls.add(poll);
    }

    public void setOwnedPolls(List<Poll> ownedPolls) {
        this.ownedPolls = ownedPolls;
    }

    public VoteUser() {}

    public VoteUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
