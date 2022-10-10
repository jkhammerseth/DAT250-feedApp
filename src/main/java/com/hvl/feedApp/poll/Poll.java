package com.hvl.feedApp.poll;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hvl.feedApp.voteUser.VoteUser;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Poll {
    @Id
    @SequenceGenerator(
            name = "poll_sequence",
            sequenceName = "poll_sequence")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "poll_sequence")
    private long pollID;
    private int yesCount;
    private int noCount;
    private String question;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startTime;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endTime;

    @ManyToOne(targetEntity = VoteUser.class)
    @JoinColumn(name = "owner_id")
    private VoteUser owner;
    @Transient
    @Enumerated(EnumType.STRING)
    private Status status;

    public VoteUser getOwnerId() {
        return owner;
    }

    public void setOwner(VoteUser owner) {
        this.owner = owner;
    }

    public Poll() {}

    public Poll(int yesCount, int noCount,VoteUser owner , String question, LocalDate startTime, LocalDate endTime) {
        this.yesCount = yesCount;
        this.noCount = noCount;
        this.owner = owner;
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
