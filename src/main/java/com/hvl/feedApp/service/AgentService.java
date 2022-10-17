package com.hvl.feedApp.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hvl.feedApp.Agent;
import com.hvl.feedApp.Enums.Role;
import com.hvl.feedApp.Vote;
import com.hvl.feedApp.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

    private static final Set<String> allowedRoles = new HashSet<String>(Arrays.asList("USER", "ADMIN", "DEVICE"));


    @Autowired
    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }
    public List<Agent> getAgents() {
        return agentRepository.findAll();
    }
    public Agent getById(Long agentID){
        return agentRepository.findById(agentID).orElseThrow(() -> new IllegalStateException("Vote with id: "+ agentID + " does not exist"));
    }

    public Agent createNewAgent(Agent agent) {
        /*
        if (agent.getUsername().isEmpty() || agent.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (agent.getPassword().isEmpty() || agent.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (agent.getEmail().isEmpty() || agent.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        Role role = agent.getRole();
        if (role == null && !allowedRoles.contains(role)) {
            throw new IllegalStateException("Role must be one of three strings: 'USER', 'ADMIN' or 'DEVICE', not: "+role);
        }
         */

        agentRepository.save(agent);
        return this.getById(agent.getAgentID());
    }

    public void deleteAgent(Long agentID) {
        // Handle related votes
//        List<Vote> agentsVotes = voteService.getVotesByAgentID(agentID);
//        for (Vote vote : agentsVotes) {
//            vote.setVoter(null);
//        }

        Optional<Agent> agentToDelete = agentRepository.findById(agentID);
        //boolean exists = agentRepository.existsById(agentID);
        if (agentToDelete.isEmpty()) {
            throw new IllegalStateException("User with id: " + agentID + " does not exist");
        }

        agentRepository.deleteById(agentID);
    }

    // TODO: Abstract to field validation function and reuse on create
    @Transactional
    public Agent updateAgent(Long agentID, String bodyString) {
        Agent agent = agentRepository.findById(agentID).orElseThrow(() -> new IllegalStateException("User with id: " + agentID + " does not exist"));

        JsonObject body = new Gson().fromJson(bodyString, JsonObject.class);

        //TODO: Error handling, field validation!
        if (body.get("username").getAsString().isEmpty() || body.get("username").getAsString().isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (body.get("email").getAsString().isEmpty() || body.get("email").getAsString().isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (body.get("password").getAsString().isEmpty() || body.get("password").getAsString().isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (body.get("role").getAsString().isEmpty() || body.get("role").getAsString().isBlank()) {
            throw new IllegalArgumentException("Role cannot be empty");
        }
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
