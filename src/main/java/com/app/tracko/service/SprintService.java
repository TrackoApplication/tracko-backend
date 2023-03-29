package com.app.tracko.service;

import com.app.tracko.model.Sprint;

import java.util.List;

public interface SprintService {
    Sprint createSprint(Sprint sprint);

    List<Sprint> getAllSprints();

    boolean deleteSprint(Long sprintId);

    Sprint getSprintById(Long sprintId);

    Sprint updateSprint(Long sprintId, Sprint sprint);
}
