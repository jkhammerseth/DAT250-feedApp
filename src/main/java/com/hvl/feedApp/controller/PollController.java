package com.hvl.feedApp.controller;

import java.util.List;

import com.google.gson.JsonObject;
import com.hvl.feedApp.Poll;
import com.hvl.feedApp.service.PollService;
import com.hvl.feedApp.service.VoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/poll")
public class PollController {
    private final VoteUserService voteUserService;
    private final PollService pollService;

    @Autowired
    public PollController(VoteUserService voteUserService, PollService pollService) {
        this.voteUserService = voteUserService;
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

    @PostMapping(params = {"owner_id"})
    public void createNewPoll(@RequestBody JsonObject poll){
        JsonObject test = poll;
        System.out.println("ditta her: "+test.get("owner"));
        //, @RequestParam Long owner_id
        //try {(owner)}
        //poll.setOwner(voteUserService.getById(owner_id));

        //pollService.createNewPoll(poll);
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
