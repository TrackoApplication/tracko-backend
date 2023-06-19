package com.app.tracko.controller;

import com.app.tracko.entity.IssueEntity;
import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.entity.TeamEntity;
import com.app.tracko.model.TeamDto;
import com.app.tracko.repository.IssueRepository;
import com.app.tracko.repository.SystemUserRepository;
import com.app.tracko.repository.TeamRepository;
import com.app.tracko.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001","http://localhost:3002","http://localhost:3002"})
@RestController
@RequestMapping("/api/v1/team")
public class TeamController {
    private final TeamService teamService;
    private final TeamRepository teamRepository;
    private final SystemUserRepository systemUserRepository;
    private final IssueRepository issueRepository;


    public TeamController(TeamService teamService, TeamRepository teamRepository, SystemUserRepository systemUserRepository, IssueRepository issueRepository) {
        this.teamService = teamService;
        this.teamRepository = teamRepository;
        this.systemUserRepository = systemUserRepository;
        this.issueRepository = issueRepository;
    }



    @PostMapping
    public TeamEntity createTeams(@RequestBody TeamEntity team){
        return teamService.createTeams(team);
    }

    @GetMapping
    public List<TeamEntity> getAllTeams(){
        return teamService.getAllTeams();
    }

    @PutMapping("/AddUsersToTeam")
    public ResponseEntity<String> addUsersToTeam(@RequestParam Long id,
                                                       @RequestParam List<Long> id2) {
        TeamEntity a = teamRepository.findById(id).get();
        List<SystemUserEntity> b = new ArrayList<>();
        for(Long x : id2) {
            SystemUserEntity y = systemUserRepository.findById(x).get();
            b.add(y);
        }
        a.setMembers(b);
        teamRepository.save(a);

        return ResponseEntity.ok("Successfully Updated Team");
    }

    @PutMapping("/AddIssuesToTeam")
    public ResponseEntity<String> addIssuesToTeam(@RequestParam Long id,
                                                @RequestParam List<Long> id2) {
        TeamEntity a = teamRepository.findById(id).get();
        List<IssueEntity> b = new ArrayList<>();
        for(Long x : id2) {
            IssueEntity y = issueRepository.findById(x).get();
            b.add(y);
        }
        a.setTeamIssues(b);
        teamRepository.save(a);

        return ResponseEntity.ok("Successfully Updated Team");
    }

//    @GetMapping("/teamSummary")
//    public List<TeamDto> getTeamSummary(){
//
//
//    }




    //team summary - dashboard
    @GetMapping("/teamSummary/{id}")
    public List<TeamDto> getTeamSummary(@PathVariable Long id){
        return teamService.getTeamSummary(id);
    }

    @GetMapping("/getScrumMastername")
    public String getScrumMasterName (@RequestParam Long id){
        return teamService.getScrumMasterName(id);
    }

    @GetMapping("/count")
    public long getTeamCount(){
        return teamService.getTeamCount();
    }


}
