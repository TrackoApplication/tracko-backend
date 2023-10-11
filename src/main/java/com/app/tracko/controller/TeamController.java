package com.app.tracko.controller;

import com.app.tracko.entity.TeamEntity;
import com.app.tracko.model.Team;
import com.app.tracko.repository.TeamRepository;
import com.app.tracko.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v9/teams")

public class TeamController {
    private final TeamService teamService;
    private final TeamRepository teamRepository;
    @Autowired
    public TeamController(TeamService teamService, TeamRepository teamRepository) {
        this.teamService = teamService;
        this.teamRepository = teamRepository;
    }

    @PostMapping
    public TeamEntity createTeam(@RequestBody TeamEntity teamEntity){
        return teamRepository.save(teamEntity);
    }

    @GetMapping
    public List<TeamEntity> getTeam(){
        return teamRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTeam(@PathVariable Long id) {
        boolean deleted = teamService.deleteTeam(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        return ResponseEntity.ok(team);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        team = teamService.updateTeam(id, team);
        return ResponseEntity.ok(team);
    }

    @GetMapping("/existing-teams")
    public List<Team> getExistingTeams() {
        return teamService.getAllTeams();
    }
}
