package com.hvl.feedApp.vote;


import com.hvl.feedApp.poll.Poll;
import com.hvl.feedApp.voteUser.VoteUser;

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
    private Answer answer;
    @OneToOne
    private VoteUser voter;
    @OneToOne
    private Poll poll;

    public Vote() {
    }

    public Vote(Answer answer, VoteUser voter, Poll poll) {
        this.answer = answer;
        this.voter = voter;
        this.poll = poll;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
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
