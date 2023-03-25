package com.app.tracko.service;

import com.app.tracko.entity.SprintEntity;
import com.app.tracko.model.Sprint;
import com.app.tracko.repository.SprintRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SprintServiceImpl implements SprintService {
    private SprintRepository sprintRepository;

    public SprintServiceImpl(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Override
    public Sprint createSprint(Sprint sprint) {
        SprintEntity sprintEntity = new SprintEntity();

        BeanUtils.copyProperties(sprint,sprintEntity);
        sprintRepository.save(sprintEntity);
        return sprint;
    }

    @Override
    public List<Sprint> getAllSprints() {
        List<SprintEntity> sprintEntities
                = sprintRepository.findAll();

        List<Sprint> sprints = sprintEntities
                .stream()
                .map(spr -> new Sprint(spr.getSprintId(),
                        spr.getSprintName(),
                        spr.getDuration(),
                        spr.getStartDate(),
                        spr.getEndDate(),
                        spr.getSprintGoal()))
                .collect(Collectors.toList());
        return sprints;
    }
}
