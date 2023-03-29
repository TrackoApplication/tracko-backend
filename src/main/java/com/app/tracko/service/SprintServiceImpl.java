package com.app.tracko.service;

import com.app.tracko.entity.SprintEntity;
import com.app.tracko.model.Sprint;
import com.app.tracko.repository.SprintRepository;
import com.sun.mail.iap.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
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

    @Override
    public boolean deleteSprint(Long SprintId) {
        SprintEntity sprint = sprintRepository.findById(SprintId).get();
        sprintRepository.delete(sprint);
        return true;
    }

    @Override
    public Sprint getSprintById(Long sprintId) {
        SprintEntity sprintEntity = sprintRepository.findById(sprintId).get();
        Sprint sprint = new Sprint();
        BeanUtils.copyProperties(sprintEntity,sprint);
        return sprint;
    }

    @Override
    public Sprint updateSprint(Long sprintId, Sprint sprint) {
        SprintEntity sprintEntity = sprintRepository.findById(sprintId).get();
        sprintEntity.setSprintName(sprint.getSprintName());
        sprintEntity.setStartDate(sprint.getStartDate());
        sprintEntity.setEndDate(sprint.getEndDate());
        sprintEntity.setSprintGoal(sprint.getSprintGoal());

        sprintRepository.save(sprintEntity);
        return sprint;
    }
}
