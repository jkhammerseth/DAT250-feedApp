package com.hvl.feedApp.vote;

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

    @PostMapping
    public void createVote(@RequestBody Vote vote){
        voteService.createVote(vote);
    }
    @DeleteMapping(path = "{voteID}")
    public void deleteVote(@PathVariable("voteID") Long voteID){
        voteService.deleteVote(voteID);
    }

}
