package com.hvl.feedApp.poll;

import com.hvl.feedApp.voteUser.VoteUser;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Poll {
    @Id
    @SequenceGenerator(
            name = "poll_sequence",
            sequenceName = "poll_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poll_sequence")
    private long pollID;
    private int yesCount;
    private int noCount;
    private String question;
    private LocalDate startTime;
    private LocalDate endTime;
    @ManyToOne(targetEntity = VoteUser.class)
    private VoteUser owner;
    @Transient
    @Enumerated(EnumType.STRING)
    private Status status;

    public VoteUser getOwner() {
        return owner;
    }

    public void setOwner(VoteUser owner) {
        this.owner = owner;
    }

    public Poll() {}

    public Poll(long pollID, int yesCount, int noCount, String question, LocalDate startTime, LocalDate endTime) {
        this.pollID = pollID;
        this.yesCount = yesCount;
        this.noCount = noCount;
        this.question = question;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Poll(int yesCount, int noCount,String question, LocalDate startTime, LocalDate endTime) {
        this.yesCount = yesCount;
        this.noCount = noCount;
        this.question = question;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getPollID() {
        return pollID;
    }

    public int getYesCount() {
        return yesCount;
    }

    public int getNoCount() {
        return noCount;
    }

    public Status getStatus() {
        if (startTime.isBefore(LocalDate.now()) && endTime.isAfter(LocalDate.now())) {
            return Status.ACTIVE;
        } else if (startTime.isAfter(LocalDate.now()) && endTime.isAfter(LocalDate.now())) {
            return Status.FUTURE;
        } else {
            return Status.EXPIRED;
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question){
        this.question = question;
    }

}
