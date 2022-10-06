package com.hvl.feedApp.voteUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VoteUserService {

    private final VoteUserRepository voteUserRepository;

    @Autowired
    public VoteUserService(VoteUserRepository voteUserRepository) {
        this.voteUserRepository = voteUserRepository;
    }
    public List<VoteUser> getVoteUsers() {
        return voteUserRepository.findAll();
    }
    public Optional<VoteUser> getById(Long id){
        return voteUserRepository.findById(id);
    }

    public void createNewVoteUser(VoteUser voteUser) {
        voteUserRepository.save(voteUser);
    }

    public void deleteVoteUser(Long voteUserID) {
        boolean exists = voteUserRepository.existsById(voteUserID);
        if (!exists) {
            throw new IllegalStateException("User with id: " + voteUserID + " does not exist");
        }
        voteUserRepository.deleteById(voteUserID);
    }

    @Transactional
    public void updateVoteUser(Long voteUserID, String username, String email) {
        VoteUser voteUser = voteUserRepository.findById(voteUserID).orElseThrow(() -> new IllegalStateException("User with id: " + voteUserID + " does not exist"));
        if (username != null && username.length() > 0) {
            voteUser.setUsername(username);
        }
        if (email != null && email.length() > 0) {
            voteUser.setEmail(email);
        }
    }
}
