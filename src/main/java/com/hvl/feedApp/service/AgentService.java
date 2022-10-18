package com.hvl.feedApp.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hvl.feedApp.Agent;
import com.hvl.feedApp.Enums.Role;
import com.hvl.feedApp.Poll;
import com.hvl.feedApp.Vote;
import com.hvl.feedApp.repository.AgentRepository;
import com.hvl.feedApp.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AgentService {

    private final AgentRepository agentRepository;
    private final VoteRepository voteRepository;

    private static final Set<String> allowedRoles = new HashSet<String>(Arrays.asList("USER", "ADMIN", "DEVICE"));

    @Autowired
    public AgentService(AgentRepository agentRepository, VoteRepository voteRepository) {
        this.agentRepository = agentRepository;
        this.voteRepository = voteRepository;
    }
    public List<Agent> getAgents() {
        return agentRepository.findAll();
    }
    public Agent getById(Long agentID){
        return agentRepository.findById(agentID).orElseThrow(() -> new IllegalStateException("Vote with id: "+ agentID + " does not exist"));
    }

    public Agent createNewAgent(Agent agent) {
        agentRepository.save(agent);
        return this.getById(agent.getAgentID());
    }

    public void deleteAgent(Long agentID) {
        // Find agent
        Boolean exists = agentRepository.existsById(agentID);

        if (!exists) {
            throw new IllegalStateException("User with id: " + agentID + " does not exist");
        }
        Agent agent = this.getById(agentID);

        // Find related votes
        List<Vote> agentsVotes = voteRepository.findAll();
        for (Vote vote : agentsVotes) {
            if (vote.getVoter().getAgentID()  == agent.getAgentID()) {
                voteRepository.delete(vote);
            }
        }

        agentRepository.deleteById(agentID);
    }

    // TODO: Abstract to field validation function and reuse on create
    @Transactional
    public Agent updateAgent(Long agentID, String bodyString) {
        Agent agent = agentRepository.findById(agentID).orElseThrow(() -> new IllegalStateException("User with id: " + agentID + " does not exist"));

        JsonObject body = new Gson().fromJson(bodyString, JsonObject.class);

        //TODO: Error handling, field validation!
        String username = body.get("username").getAsString();
        String email = body.get("email").getAsString();
        String password = body.get("password").getAsString();
        String role = body.get("role").getAsString();

        if (username != null && username.length() > 0) {
            agent.setUsername(username);
        }
        if (email != null && email.length() > 0) {
            agent.setEmail(email);
        }
        if (password != null) {//&& password.length() > 6) {
            agent.setPassword(password);
        }
//        else {
//            throw new IllegalStateException("Password must be a string of characters longer than 6");
//        }
        if (role != null && allowedRoles.contains(role)) {
            agent.setRole(Role.valueOf(role));
        }
        else {
            throw new IllegalStateException("Role must be one of three strings: 'USER', 'ADMIN' or 'DEVICE', not: "+role);
        }

        return this.getById(agentID);

    }
}
