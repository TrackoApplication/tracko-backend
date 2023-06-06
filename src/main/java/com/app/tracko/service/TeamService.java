package com.app.tracko.service;

import com.app.tracko.entity.TeamEntity;

import java.util.List;

public interface TeamService {
    long getTeamCount();

    TeamEntity createTeams(TeamEntity team);

    List<TeamEntity> getAllTeams();
}
