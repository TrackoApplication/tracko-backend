package com.app.tracko.service;

import com.app.tracko.entity.TeamEntity;
import com.app.tracko.model.Team;
import com.app.tracko.repository.TeamRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team createTeam(Team team) {
        TeamEntity teamEntity = new TeamEntity();
        BeanUtils.copyProperties(team, teamEntity);
        teamRepository.save(teamEntity);
        return team;
    }

    @Override
    public List<Team> getAllTeams() {
        List<TeamEntity> teamEntities = teamRepository.findAll();
        List<Team> teams = teamEntities.stream()
                .map(tea -> new Team(
                        tea.getId(),
                        tea.getTeamName(),
                        tea.getUsers(),
                        tea.getMembers()
                ))
                .collect(Collectors.toList());
        return teams;
    }

    @Override
    public boolean deleteTeam(Long id) {
        TeamEntity team = teamRepository.findById(id).orElse(null);
        if (team != null) {
            teamRepository.delete(team);
            return true;
        }
        return false;
    }

    @Override
    public Team getTeamById(Long id) {
        TeamEntity teamEntity = teamRepository.findById(id).orElse(null);
        if (teamEntity != null) {
            Team team = new Team();
            BeanUtils.copyProperties(teamEntity, team);
            return team;
        }
        return null;
    }

    @Override
    public Team updateTeam(Long id, Team team) {
        TeamEntity teamEntity = teamRepository.findById(id).orElse(null);
        if (teamEntity != null) {
            teamEntity.setTeamName(team.getTeamName());
            teamEntity.setUsers(team.getUsers());
            teamEntity.setMembers(team.getMembers());
            teamRepository.save(teamEntity);
            return team;
        }
        return null;
    }

    @Override
    public List<Team> getExistingTeams() {
        List<TeamEntity> teamEntities = teamRepository.findAll();
        List<Team> teams = teamEntities.stream()
                .map(tea -> new Team(
                        tea.getId(),
                        tea.getTeamName(),
                        tea.getUsers(),
                        tea.getMembers()
                ))
                .collect(Collectors.toList());
        return teams;
    }
}