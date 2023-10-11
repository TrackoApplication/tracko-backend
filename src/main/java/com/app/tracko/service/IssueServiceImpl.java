package com.app.tracko.service;

import com.app.tracko.entity.IssueEntity;
import com.app.tracko.entity.SprintEntity;
import com.app.tracko.model.Issue;
import com.app.tracko.repository.IssueRepository;
import com.app.tracko.repository.SprintRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements IssueService {
    private IssueRepository issueRepository;
    private SprintRepository sprintRepository;

    public IssueServiceImpl(IssueRepository issueRepository, SprintRepository sprintRepository) {
        this.issueRepository = issueRepository;
        this.sprintRepository = sprintRepository;
    }

    @Override
    public Issue createIssue(Issue issue) {
        IssueEntity issueEntity = new IssueEntity();

        BeanUtils.copyProperties(issue, issueEntity);
        issueRepository.save(issueEntity);
        return issue;
    }

    @Override
    public List<Issue> getAllIssues() {
        List<IssueEntity> issueEntities
                = issueRepository.findAll();

        List<Issue> issues = issueEntities
                .stream()
                .map(iss -> {
                    Long sprintId = iss.getSprint() != null ? iss.getSprint().getSprintId() : null;
                    return new Issue(iss.getIssueId(),
                            iss.getProjectName(),
                            iss.getIssuetypeName(),
                            iss.getSummary(),
                            iss.getDescription(),
                            iss.getAssignee(),
                            iss.getEpicName(),
                            iss.getReqOfTesting(),
                            iss.getSPDeveloping(),
                            iss.getSPTesting(),
                            iss.getTotalSP(),
                            iss.getPriority(),
                            iss.getReporter(),
                            iss.getStatus(),
                            iss.getSprintName(),
                            sprintId);
                })
                .collect(Collectors.toList());

        return issues;
    }

    @Override
    public boolean deleteIssue(Long IssueId) {
        IssueEntity issue = issueRepository.findById(IssueId).get();
        issueRepository.delete(issue);
        return true;
    }

    @Override
    public Issue getIssueById(Long issueId) {
        IssueEntity issueEntity = issueRepository.findById(issueId).get();
        Issue issue = new Issue();
        BeanUtils.copyProperties(issueEntity, issue);
        return issue;
    }

    @Override
    public Issue updateIssue(Long issueId, Issue issue) {
        IssueEntity issueEntity = issueRepository.findById(issueId).get();
        issueEntity.setStatus(issue.getStatus());

        issueRepository.save(issueEntity);
        return issue;
    }

    @Override
    public Issue updateIssueSprint(Long issueId, Issue updatedIssue) {
        IssueEntity issueEntity = issueRepository.findById(issueId).get();
        SprintEntity sprintEntity = sprintRepository.findById(updatedIssue.getSprintId()).orElseThrow(NoSuchElementException::new);
        issueEntity.setSprint(sprintEntity);
        issueRepository.save(issueEntity);

        return updatedIssue;
    }
}
    //Newly added
//    @Override
//    public Issue updateIssueSprint(Long issueId, Issue updatedIssue) {
//        IssueEntity issueEntity = issueRepository.findById(issueId).orElseThrow(NoSuchElementException::new);
//        SprintEntity sprintEntity = sprintRepository.findById(updatedIssue.getSprintid()).orElseThrow(NoSuchElementException::new);
//        issueEntity.setSprint(sprintEntity);
//        issueRepository.save(issueEntity);
//
//        return updatedIssue;
//    }

//iss.getSprint().getSprintId()

//    List<Issue> issues = issueEntities
//            .stream()
//            .map(iss -> new Issue(iss.getIssueId(),
//                    iss.getProjectName(),
//                    iss.getIssueType(),
//                    iss.getSummary(),
//                    iss.getDescription(),
//                    iss.getAssignee(),
//                    iss.getEpicName(),
//                    iss.getReqOfTesting(),
//                    iss.getSPDeveloping(),
//                    iss.getSPTesting(),
//                    iss.getTotalSP(),
//                    iss.getPriority(),
//                    iss.getReporter(),
//                    iss.getStatus(),
//                    iss.getSprint().getSprintId()))
//            .collect(Collectors.toList());



