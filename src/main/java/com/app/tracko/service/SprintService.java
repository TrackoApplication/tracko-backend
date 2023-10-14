package com.app.tracko.service;

import com.app.tracko.model.Issue;
import com.app.tracko.model.Sprint;

import java.util.List;

public interface SprintService {
    Sprint createSprint(Sprint sprint);

    List<Sprint> getAllSprints();

    boolean deleteSprint(Long sprintId);

    Sprint getSprintById(Long sprintId);

    Sprint updateSprint(Long sprintId, Sprint sprint);

    //Newly added
//    Sprint updateIssueSprint(Long sprintId, Issue updatedIssue);

}
