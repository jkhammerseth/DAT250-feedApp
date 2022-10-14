package com.hvl.feedApp.controller;

import com.hvl.feedApp.Agent;
import com.hvl.feedApp.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/agents")
public class AgentController {

    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService){
        this.agentService = agentService;
    }

    @GetMapping()
    public List<Agent> getAgents(){
        return agentService.getAgents();
    }
    @GetMapping(path = "{id}")
    public Agent getById(@PathVariable("id") Long agentID){
        return agentService.getById(agentID);
    }
    @PostMapping
    public void createNewAgent(@RequestBody Agent agent){
        agentService.createNewAgent(agent);
    }
    @DeleteMapping(path = "{id}")
    public void deleteAgent(@PathVariable("id") Long agentID){
        agentService.deleteAgent(agentID);
    }
    @PutMapping(path = "{id}")
    public void updateAgent(
            @PathVariable("id") Long agentID,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password){
                agentService.updateAgent(agentID, username, email, password);
    }

}
