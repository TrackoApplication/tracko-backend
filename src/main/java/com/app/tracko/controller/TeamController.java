package com.app.tracko.controller;

import com.app.tracko.entity.TeamEntity;
import com.app.tracko.model.Issue;
import com.app.tracko.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/count")
    public long getTeamCount(){
        return teamService.getTeamCount();
    }

    @PostMapping
    public TeamEntity createTeams(@RequestBody TeamEntity team){
        return teamService.createTeams(team);
    }

    @GetMapping
    public List<TeamEntity> getAllTeams(){
        return teamService.getAllTeams();
    }


}
