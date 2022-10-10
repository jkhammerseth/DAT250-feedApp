package com.hvl.feedApp.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Vote> getVoteById(Long voteID){
        return voteRepository.findById(voteID);
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
}
