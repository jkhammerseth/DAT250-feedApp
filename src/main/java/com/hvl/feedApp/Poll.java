package com.hvl.feedApp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hvl.feedApp.Enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Poll {
    public Poll() {}

    public Poll(boolean isPrivate, int pin, LocalDateTime startTime, LocalDateTime endTime, int yesCount, int noCount, String question, Status status) {
        this.isPrivate = isPrivate;
        this.pin = pin;
        this.startTime = startTime;
        this.endTime = endTime;
        this.yesCount = yesCount;
        this.noCount = noCount;
        this.question = question;
        this.status = status;

        // Setting status
        StatusHandler statusHandler = new StatusHandler();
        this.status = statusHandler.getStatus(this.startTime, this.endTime);
    }

    @Id
    @SequenceGenerator(
            name = "poll_sequence",
            sequenceName = "poll_sequence")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "poll_sequence")
    @Column(name="poll_id")
    private long pollID;

    // Relation
    @ManyToOne(targetEntity = Agent.class)
    private Agent owner;

    // Attributes
    private boolean isPrivate;
    private int pin;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private int yesCount;
    private int noCount;
    private String question;
    //@Transient
    @Enumerated(EnumType.STRING)
    private Status status;

    public void setPollID(long pollID) {
        this.pollID = pollID;
    }

    public void refreshStatus() {
        StatusHandler statusHandler = new StatusHandler();
        this.status = statusHandler.getStatus(this.startTime, this.endTime);
/*        if (startTime.isBefore(LocalDateTime.now()) && endTime.isAfter(LocalDateTime.now())) {
            this.status = Status.ACTIVE;
        } else if (startTime.isAfter(LocalDateTime.now()) && endTime.isAfter(LocalDateTime.now())) {
            this.status = Status.FUTURE;
        } else {
            this.status = Status.EXPIRED;
        }*/
    }

    public Agent getOwner() {
        return owner;
    }

    public void setOwner(Agent owner) {
        this.owner = owner;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public long getPollID() {
        return pollID;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getYesCount() {
        return yesCount;
    }

    public void setYesCount(int yesCount) {
        this.yesCount = yesCount;
    }

    public int getNoCount() {
        return noCount;
    }

    public void setNoCount(int noCount) {
        this.noCount = noCount;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

class StatusHandler {
    public static Status getStatus(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isBefore(LocalDateTime.now()) && endTime.isAfter(LocalDateTime.now())) {
            return Status.ACTIVE;
        } else if (startTime.isAfter(LocalDateTime.now()) && endTime.isAfter(LocalDateTime.now())) {
            return Status.FUTURE;
        } else {
            return Status.EXPIRED;
        }
    }

}

