package com.app.tracko.service;

import com.app.tracko.entity.SprintIssueEntity;
import com.app.tracko.model.SprintIssue;
import com.app.tracko.repository.SprintIssueRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SprintIssueServiceImpl implements SprintIssueService{
    private SprintIssueRepository sprintissueRepository;

    public SprintIssueServiceImpl(SprintIssueRepository sprintissueRepository) {
        this.sprintissueRepository = sprintissueRepository;
    }

    @Override
    public SprintIssue createSprintIssue(SprintIssue sprintissue) {
        SprintIssueEntity sprintissueEntity = new SprintIssueEntity();

        BeanUtils.copyProperties(sprintissue,sprintissueEntity);
        sprintissueRepository.save(sprintissueEntity);
        return sprintissue;
    }

    @Override
    public List<SprintIssue> getAllSprintIssues() {
        List<SprintIssueEntity> sprintissueEntities
                = sprintissueRepository.findAll();

        List<SprintIssue> sprintissues = sprintissueEntities
                .stream()
                .map(iss -> new SprintIssue(iss.getSprintIssueId(),
                        iss.getProjectName(),
                        iss.getIssueType(),
                        iss.getSummary(),
                        iss.getDescription(),
                        iss.getAssignee(),
                        iss.getSprintName(),
                        iss.getEpicName(),
                        iss.getReqOfTesting(),
                        iss.getSPDeveloping(),
                        iss.getSPTesting(),
                        iss.getTotalSP(),
                        iss.getPriority(),
                        iss.getReporter()))
                .collect(Collectors.toList());
        return sprintissues;
    }

    @Override
    public boolean deleteSprintIssue(Long SprintIssueId) {
        SprintIssueEntity sprintissue = sprintissueRepository.findById(SprintIssueId).get();
        sprintissueRepository.delete(sprintissue);
        return true;
    }
}
