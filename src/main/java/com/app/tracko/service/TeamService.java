package com.app.tracko.service;

import com.app.tracko.entity.TeamEntity;
import com.app.tracko.model.TeamDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TeamService {
    long getTeamCount();

    TeamEntity createTeams(TeamEntity team);

    String getScrumMasterName(@RequestParam Long id);

    List<TeamEntity> getAllTeams();

    List<TeamDto> getTeamSummary(Long id);
}
