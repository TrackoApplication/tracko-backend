package com.app.tracko.service;

import com.app.tracko.entity.TeamEntity;
import com.app.tracko.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
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

    @Override
    public List<TeamEntity> getAllTeams() {
        return teamRepository.findAll();
    }
}
