package com.hvl.feedApp.controller;

import java.util.List;

import com.hvl.feedApp.Poll;
import com.hvl.feedApp.service.AgentService;
import com.hvl.feedApp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/poll")
public class PollController {
    private final AgentService agentService;
    private final PollService pollService;

    @Autowired
    public PollController(AgentService agentService, PollService pollService) {
        this.agentService = agentService;
        this.pollService = pollService;
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
        System.out.println("dis work?"+poll.toString());
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
}
