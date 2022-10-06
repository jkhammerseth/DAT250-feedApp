package com.hvl.feedApp.poll;

import java.util.List;
import java.util.Optional;

import com.hvl.feedApp.voteUser.VoteUser;
import com.hvl.feedApp.voteUser.VoteUserService;
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

    @PostMapping(path = "{voteUserID}")
    public void createNewPoll(@RequestBody Poll poll,@PathVariable("voteUserID") Long voteUserID){
        try {
        Optional<VoteUser> voteUser = voteUserService.getById(voteUserID);
        voteUser.ifPresentOrElse(
                (owner) -> {
                    poll.setOwner(owner);
                    owner.addOwnedPoll(poll);
                    },
                () -> {System.out.println("when does this print?");});
        }
        catch (Exception e){
            System.out.println("User does not exist");
        }
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
