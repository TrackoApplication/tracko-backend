package com.app.tracko.service;

import com.app.tracko.entity.IssueEntity;
import com.app.tracko.entity.SprintEntity;
import com.app.tracko.model.Issue;
import com.app.tracko.model.Sprint;
import com.app.tracko.repository.IssueRepository;
import com.app.tracko.repository.SprintRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SprintServiceImpl implements SprintService {
    private SprintRepository sprintRepository;
    private IssueRepository issueRepository;

    public SprintServiceImpl(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
        this.issueRepository = issueRepository;
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

//    @Override
//    public boolean deleteSprint(Long sprintId) {
//        Optional<SprintEntity> optionalSprint = sprintRepository.findById(sprintId);
//        if (optionalSprint.isPresent()) {
//            SprintEntity sprint = optionalSprint.get();
//            sprintRepository.delete(sprint);
//            return true;
//        } else {
//            return false; // Sprint not found
//        }
//    }

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

    //Newly added
//    @Override
//    public Sprint updateIssueSprint(Long sprintId, Issue updatedIssue) {
//        SprintEntity sprintEntity = sprintRepository.findById(sprintId).orElseThrow(NoSuchElementException::new);
//
//        // Retrieve the issue from the database using the IssueId from the updatedIssue
//        IssueEntity issueEntity = issueRepository.findById(updatedIssue.getIssueId()).orElseThrow(NoSuchElementException::new);
//
//        // Update the issue's sprint association
//        issueEntity.setSprint(sprintEntity);
//
//        // Save the updated issue
//        issueRepository.save(issueEntity);
//
//        return convertSprintEntityToSprint(sprintEntity);
//    }

    // Utility method to convert SprintEntity to Sprint
//    private Sprint convertSprintEntityToSprint(SprintEntity sprintEntity) {
//        Sprint sprint = new Sprint();
//        BeanUtils.copyProperties(sprintEntity, sprint);
//        return sprint;
//    }

}
