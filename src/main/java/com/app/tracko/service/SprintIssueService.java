package com.app.tracko.service;

import com.app.tracko.model.SprintIssue;

import java.util.List;

public interface SprintIssueService {
    SprintIssue createSprintIssue(SprintIssue sprintissue);

    List<SprintIssue> getAllSprintIssues();

    boolean deleteSprintIssue(Long SprintIssueId);
}
