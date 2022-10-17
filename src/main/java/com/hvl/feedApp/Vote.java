package com.hvl.feedApp;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class Vote {
    @Id
    @SequenceGenerator(
            name = "vote_sequence",
            sequenceName = "vote_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vote_sequence")
    @Column(name="vote_id")
    private long voteID;
    private boolean answer;
    @ManyToOne//(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Agent voter;
    @ManyToOne//(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Poll poll;

    public Vote(boolean answer, Agent voter, Poll poll) {
        this.answer = answer;
        this.voter = voter;
        this.poll = poll;
    }

    public long getVoteID() {
        return voteID;
    }
    public void setVoteID(long voteID) {
        this.voteID = voteID;
    }
    public boolean isAnswer() {
        return answer;
    }
    public Vote() {}
    public boolean getAnswer() {
        return answer;
    }
    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
    public Agent getVoter() {
        return voter;
    }

    public void setVoter(Agent voter) {
        this.voter = voter;
    }
    public Poll getPoll() {
        return poll;
    }
    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
