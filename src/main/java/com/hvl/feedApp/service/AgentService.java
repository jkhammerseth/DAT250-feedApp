package com.hvl.feedApp.service;

import com.hvl.feedApp.Agent;
import com.hvl.feedApp.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

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

    public void createNewAgent(Agent agent) {
        agentRepository.save(agent);
    }

    public void deleteAgent(Long agentID) {
        boolean exists = agentRepository.existsById(agentID);
        if (!exists) {
            throw new IllegalStateException("User with id: " + agentID + " does not exist");
        }
        agentRepository.deleteById(agentID);
    }

    @Transactional
    public void updateAgent(Long agentID, String username, String email, String password) {
        Agent agent = agentRepository.findById(agentID).orElseThrow(() -> new IllegalStateException("User with id: " + agentID + " does not exist"));
        if (username != null && username.length() > 0) {
            agent.setUsername(username);
        }
        if (email != null && email.length() > 0) {
            agent.setEmail(email);
        }
        if (password != null && password.length() > 6) {
            agent.setEmail(email);
        }
        else {
            throw new IllegalStateException("Password must be a string of characters longer than 6");
        }

    }
}
