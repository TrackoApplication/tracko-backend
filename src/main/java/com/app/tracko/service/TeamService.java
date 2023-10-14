package com.app.tracko.service;

import com.app.tracko.model.Team;

import java.util.List;

public interface TeamService {
    Team createTeam(Team team);

    List<Team> getAllTeams();

    boolean deleteTeam(Long id);

    Team getTeamById(Long id);

    Team updateTeam(Long id, Team team);

    List<Team> getExistingTeams();
}