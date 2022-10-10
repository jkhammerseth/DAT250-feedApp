package com.hvl.feedApp;


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
    private long voteID;
    private boolean answer;
    @OneToOne
    private VoteUser voter;
    @OneToOne
    private Poll poll;

    public Vote() {
    }

    public Vote(boolean answer, VoteUser voter, Poll poll) {
        this.answer = answer;
        this.voter = voter;
        this.poll = poll;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public VoteUser getVoter() {
        return voter;
    }

    public void setVoter(VoteUser voter) {
        this.voter = voter;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
