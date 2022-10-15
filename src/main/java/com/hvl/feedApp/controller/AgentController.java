package com.hvl.feedApp.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
    public Agent createNewAgent(@RequestBody Agent agent){
        return agentService.createNewAgent(agent);
    }
    @DeleteMapping(path = "{id}")
    public void deleteAgent(@PathVariable("id") Long agentID){
        agentService.deleteAgent(agentID);
    }
    @PutMapping(path = "{id}")
    public Agent updateAgent(
            @PathVariable("id") Long agentID,
            @RequestBody String bodyString){

                return agentService.updateAgent(agentID, bodyString);
    }

}
