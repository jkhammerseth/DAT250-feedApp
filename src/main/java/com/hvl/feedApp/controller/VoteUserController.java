package com.hvl.feedApp.controller;

import com.hvl.feedApp.VoteUser;
import com.hvl.feedApp.service.VoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/voteUser")
public class VoteUserController {

    private final VoteUserService voteUserService;

    @Autowired
    public VoteUserController(VoteUserService voteUserService){
        this.voteUserService = voteUserService;
    }

    @GetMapping()
    public List<VoteUser> getVoteUsers(){
        return voteUserService.getVoteUsers();
    }
    @GetMapping(path = "{voteUserID}")
    public VoteUser getById(@PathVariable("voteUserID") Long voteUserID){
        return voteUserService.getById(voteUserID);
    }
    @PostMapping
    public void createNewVoteUser(@RequestBody VoteUser voteUser){
        voteUserService.createNewVoteUser(voteUser);
    }
    @DeleteMapping(path = "{voteUserID}")
    public void deleteVoteUser(@PathVariable("voteUserID") Long voteUserID){
        voteUserService.deleteVoteUser(voteUserID);
    }
    @PutMapping(path = "{voteUserID}")
    public void updateVoteUser(
            @PathVariable("voteUserID") Long voteUserID,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email){
                voteUserService.updateVoteUser(voteUserID, username, email);
    }

}
