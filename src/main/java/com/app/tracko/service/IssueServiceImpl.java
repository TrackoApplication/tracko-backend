package com.app.tracko.service;

import com.app.tracko.entity.IssueEntity;
import com.app.tracko.model.Issue;
import com.app.tracko.repository.IssueRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements IssueService{
    private IssueRepository issueRepository;

    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public Issue createIssue(Issue issue) {
        IssueEntity issueEntity = new IssueEntity();

        BeanUtils.copyProperties(issue,issueEntity);
        issueRepository.save(issueEntity);
        return issue;
    }

    @Override
    public List<Issue> getAllIssues() {
        List<IssueEntity> issueEntities
                = issueRepository.findAll();

        List<Issue> issues = issueEntities
                .stream()
                .map(iss -> new Issue(iss.getIssueId(),
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
        return issues;
    }
}
