package com.hvl.feedApp.poll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/poll")
public class PollController {

    private final PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping()
    public List<Poll> getPolls(){
        return pollService.getPolls();
    }

    @PostMapping
    public void createNewPoll(@RequestBody Poll poll){
        pollService.createNewPoll(poll);
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
