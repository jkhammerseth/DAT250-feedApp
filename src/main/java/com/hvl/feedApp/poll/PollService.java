package com.hvl.feedApp.poll;

import com.hvl.feedApp.voteUser.VoteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@Service
public class PollService {

    private final PollRepository pollRepository;

    @Autowired
    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public List<Poll> getPolls(){
        return pollRepository.findAll();
    }

    public void createNewPoll(Poll poll) {
        pollRepository.save(poll);
    }

    public void deletePoll(Long pollID) {
        boolean exists = pollRepository.existsById(pollID);
        if (!exists){
            throw new IllegalStateException("Poll with id: "+ pollID + " does not exist");
        }
        pollRepository.deleteById(pollID);
    }
    @Transactional
    public void updatePoll(Long pollID, String question) {
        Poll poll = pollRepository.findById(pollID).orElseThrow(() -> new IllegalStateException("Poll with id: "+ pollID + " does not exist"));
        if (question != null && question.length()>0){
            poll.setQuestion(question);
        }

    }
}
