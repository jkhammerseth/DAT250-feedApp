package com.hvl.feedApp.service;

import com.hvl.feedApp.repository.VoteRepository;
import com.hvl.feedApp.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository){
        this.voteRepository = voteRepository;
    }

    public List<Vote> getVotes(){
        return voteRepository.findAll();
    }

    public Vote getVoteById(Long voteID){
        return voteRepository.findById(voteID).orElseThrow(() -> new IllegalStateException("Vote with id: "+ voteID + " does not exist"));
    }
    public void createVote(Vote vote){
        voteRepository.save(vote);
    }
    public void deleteVote(Long voteID){
        boolean exists = voteRepository.existsById(voteID);
        if (!exists){
            throw new IllegalStateException("Vote with id: "+ voteID + " does not exist");
        }
        voteRepository.deleteById(voteID);
    }
    @Transactional
    public void updateVoteById(Long voteID){
        voteRepository.findById(voteID).orElseThrow(() -> new IllegalStateException("Vote with id: "+ voteID + " does not exist"));
        //do changes to vote here.. vote.setAnswer etc
    }

}
