package com.hvl.feedApp;


import javax.persistence.*;

@Entity
@Table
public class Vote {
    public Vote(boolean answer, Agent voter, Poll poll) {
        this.answer = answer;
        this.voter = voter;
        this.poll = poll;
    }

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
    @OneToOne
    private Agent voter;
    @OneToOne
    private Poll poll;

    public Vote() {
    }

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
