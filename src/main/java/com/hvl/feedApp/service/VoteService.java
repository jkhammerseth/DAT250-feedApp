package com.hvl.feedApp.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hvl.feedApp.Agent;
import com.hvl.feedApp.Poll;
import com.hvl.feedApp.repository.PollRepository;
import com.hvl.feedApp.repository.VoteRepository;
import com.hvl.feedApp.Vote;
import com.hvl.feedApp.service.AgentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import com.hvl.feedApp.controller.PollController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository){
        this.voteRepository = voteRepository;
    }

    @Autowired
    AgentService agentService;

    @Autowired
    PollService pollService;

    public List<Vote> getVotes(){
        return voteRepository.findAll();
    }

    public Vote getVoteById(Long voteID){
        return voteRepository.findById(voteID).orElseThrow(() -> new IllegalStateException("Vote with id: "+ voteID + " does not exist"));
    }

    public List<Vote> getVotesByPollID(Long pollID){
        List<Vote> votes = voteRepository.findAll();

        List<Vote> voteByPollID = new ArrayList<Vote>();

        for(Vote vote : votes){
            if (vote.getPoll().getPollID() == pollID) {
                voteByPollID.add(vote);
            }
        }

        return voteByPollID;
    }

    public List<Vote> getVotesByAgentID(Long agentID){
        List<Vote> votes = voteRepository.findAll();//TODO: findBy map to preserve memory

        List<Vote> votesByAgentID = new ArrayList<Vote>();

        for(Vote vote : votes){
            if (vote.getVoter().getAgentID() == agentID) {
                votesByAgentID.add(vote);
            }
        }

        return votesByAgentID;
    }

    public Vote createVote(Long pollID, String voteString){
        JsonObject voteJson = new Gson().fromJson(voteString, JsonObject.class);

        //TODO: Error handling, field validation!
        Long voterID = voteJson.get("voter_id").getAsLong(); // "voter_id":2,
        boolean answerYes = voteJson.get("answer_yes").getAsBoolean();

        Agent voter = agentService.getById(voterID);
        Poll poll = pollService.getPollById(pollID);

        // increment or decrement poll answer count
        if (answerYes) {
            poll.setYesCount(poll.getYesCount()+1);
        }else {
            poll.setNoCount(poll.getNoCount()+1);
        }

        Vote vote =  new Vote(answerYes, voter, poll);
        voteRepository.save(vote);
        return vote;
    }



    public void deleteVote(Long voteID){
        boolean exists = voteRepository.existsById(voteID);
        if (!exists){
            throw new IllegalStateException("Vote with id: "+ voteID + " does not exist");
        }
        voteRepository.deleteById(voteID);
    }
    @Transactional
    public Vote updateVoteById(Long voteID, String bodyString){
        Vote vote = voteRepository.findById(voteID).orElseThrow(() -> new IllegalStateException("Vote with id: "+ voteID + " does not exist"));

        Poll poll = vote.getPoll();
        boolean wasYes = vote.getAnswer();

        JsonObject voteJson = new Gson().fromJson(bodyString, JsonObject.class);

        //TODO: Error handling, field validation!
        Long voterID = voteJson.get("voter_id").getAsLong(); // "voter_id":2,
        boolean answerYes = voteJson.get("answer_yes").getAsBoolean();

        Agent voter = agentService.getById(voterID);
        //Poll poll = pollService.getPollById(pollID);

        // increment or decrement poll answer count
        if (answerYes) {
            poll.setYesCount(poll.getYesCount()+1);
        }else if (!answerYes) {
            poll.setNoCount(poll.getNoCount()+1);
        }
        if (wasYes) {
            poll.setYesCount(poll.getYesCount()-1);
        }else if (!wasYes) {
            poll.setNoCount(poll.getNoCount()-1);
        }

        Vote updatedVote =  new Vote(answerYes, voter, poll);
        return voteRepository.save(updatedVote);
        //return updatedVote;
        //do changes to vote here.. vote.setAnswer etc


    }

}
