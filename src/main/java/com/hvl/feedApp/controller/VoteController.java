package com.hvl.feedApp.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hvl.feedApp.Agent;
import com.hvl.feedApp.Vote;
import com.hvl.feedApp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/vote")
public class VoteController {

    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService){
        this.voteService = voteService;
    }
    @GetMapping
    public List<Vote> getVotes(){
        return voteService.getVotes();
    }

    @GetMapping(path = "{voteID}")
    public Vote getVoteById(@PathVariable("voteID") Long voteID){
        return voteService.getVoteById(voteID);
    }

    @PutMapping(path = "{voteID}")
    public void updateVoteById(@PathVariable("voteID") Long voteID ,@RequestBody String bodyString){voteService.updateVoteById(voteID, bodyString);}

    @DeleteMapping(path = "{voteID}")
    public void deleteVote(@PathVariable("voteID") Long voteID){
        voteService.deleteVote(voteID);
    }

}
