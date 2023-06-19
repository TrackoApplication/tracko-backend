package com.app.tracko.service;

import com.app.tracko.entity.*;
import com.app.tracko.model.TeamDto;
import com.app.tracko.repository.ProjectRepository;
import com.app.tracko.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    private final TeamRepository teamRepository;
    private final ProjectRepository projectRepository;

    public TeamServiceImpl(TeamRepository teamRepository, ProjectRepository projectRepository) {
        this.teamRepository = teamRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public long getTeamCount() {
        return teamRepository.count();
    }

    @Override
    public TeamEntity createTeams(TeamEntity team) {
        teamRepository.save(team);
        return team;
    }

    public List<IssueEntity> issuesRelatedToTeams(@RequestParam Long id){
        TeamEntity teamEntity = teamRepository.findById(id).get();
        return teamEntity.getTeamIssues();
    }
    public List<SystemUserEntity> membersRelatedToTeams(@RequestParam Long id){
        TeamEntity teamEntity = teamRepository.findById(id).get();
        return teamEntity.getMembers();
    }

    @Override
    public String getScrumMasterName(@RequestParam Long id) {
        String scrumMasterName = null;
        TeamEntity teamEntity = teamRepository.findById(id).orElse(null);
        if (teamEntity != null) {
            List<SystemUserEntity> systemUserEntities = teamEntity.getMembers();
            for (SystemUserEntity userEntity : systemUserEntities) {
                List<AccessGroupEntity> accessGroupEntities = userEntity.getAccessGroupEntities();
                for (AccessGroupEntity groupEntity : accessGroupEntities) {
                    if (groupEntity.getAccessGroupName().equals("Scrum Master")) {
                        scrumMasterName = userEntity.getFirstName();
                        break; // Exit the inner loop once the Scrum Master is found
                    }
                }
                if (scrumMasterName != null) {
                    break; // Exit the outer loop once the Scrum Master is found
                }
            }
        }
        return scrumMasterName;
    }


    @Override
    public List<TeamEntity> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public List<TeamDto> getTeamSummary(Long id2){
        ProjectEntity project = projectRepository.findById(id2).get();
        List<TeamEntity> teamEntities = project.getProjecctTeams();
        List<TeamDto> teamDtos = new ArrayList<>();

        for(TeamEntity t : teamEntities){
            TeamDto teamDto = new TeamDto();
            Long id = t.getTeamId();
            List<IssueEntity> issueEntities = issuesRelatedToTeams(id);
            teamDto.setInvolvedIssues((long) issueEntities.size());
            teamDto.setTeamName(t.getTeamName());
            List<SystemUserEntity> systemUserEntities = membersRelatedToTeams(id);
            teamDto.setNoOfMembers((long) systemUserEntities.size());
            teamDto.setTeamId(t.getTeamId());
            teamDto.setScrumMasterName(getScrumMasterName(id));
            teamDtos.add(teamDto);
        }

       return  teamDtos;

    }


}
