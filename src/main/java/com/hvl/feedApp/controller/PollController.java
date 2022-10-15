package com.hvl.feedApp.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hvl.feedApp.Agent;
import com.hvl.feedApp.Poll;
import com.hvl.feedApp.Vote;
import com.hvl.feedApp.service.AgentService;
import com.hvl.feedApp.service.PollService;
import com.hvl.feedApp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationNotSupportedException;

@RestController
@RequestMapping(path = "/polls")
public class PollController {
    private final AgentService agentService;
    private final PollService pollService;
    private final VoteService voteService;

    @Autowired
    public PollController(AgentService agentService, PollService pollService, VoteService voteService) {
        this.agentService = agentService;
        this.pollService = pollService;
        this.voteService = voteService;
    }

    @GetMapping()
    public List<Poll> getPolls(){
        return pollService.getPolls();
    }

    @GetMapping(path = "{pollID}")
    public Poll getPollById(@PathVariable("pollID") Long pollID){
        return pollService.getPollById(pollID);
    }

    @PostMapping("")
    public ResponseEntity<Poll> createNewPoll(@RequestBody Poll poll){
        //JsonObject test = poll;
        //System.out.println("ditta her: "+test.get("owner"));
        //, @RequestParam Long owner_id
        //try {(owner)}
        //poll.setOwner(voteUserService.getById(owner_id));
        //System.out.println("dis work?"+poll.toString());
        return new ResponseEntity<Poll>(pollService.createNewPoll(poll), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{pollID}")
    public void deletePoll(@PathVariable("pollID") Long pollID){
        pollService.deletePoll(pollID);
    }

    @PutMapping(path = "{pollID}")
    public void updatePoll(
            @PathVariable("pollID") Long pollID,
            @RequestParam(required = false) String question){
        pollService.updatePoll(pollID, question);
    }

    // Vote handling
    @PostMapping(path="{pollID}/votes")
    public Vote createVote(
            @PathVariable Long pollID,
            @RequestBody String voteString){

        return voteService.createVote(pollID, voteString);
    }

    @GetMapping(path="{pollID}/votes")
    public List<Vote> getVotes(
            @PathVariable Long pollID
    )
    /*
    Get all votes from poll {pollID}
    :param: pollIO: Long pollID
    :return: List of votes by corresponding pollID
     */
    {
        List<Vote> votes = voteService.getVotesByPollID(pollID);
        return votes;
    }

}
