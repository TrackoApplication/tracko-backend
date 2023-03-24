package com.app.tracko.service;

import com.app.tracko.model.Issue;
import com.app.tracko.entity.IssueEntity;
import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.repository.IssueRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements IssueService{
    private final IssueRepository issueRepository;

    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public List<Issue> getAllIssues() {
        List<IssueEntity> issueEntities
                = issueRepository.findAll();
        List<Issue> issues= issueEntities.stream().
                map(issue-> new Issue(
                        issue.getIssueId(),
                        issue.getProjectName(),
                        issue.getIssueType(),
                        issue.getSummary(),
                        issue.getDescription(),
                        issue.getAssignee(),
                        issue.getSprintName(),
                        issue.getEpicName(),
                        issue.getReqOfTesting(),
                        issue.getSPDeveloping(),
                        issue.getSPTesting(),
                        issue.getTotalSP(),
                        issue.getPriority(),
                        issue.getProgress(),
                        issue.getReporter())).collect(Collectors.toList()
                );
        return issues;
    }

    @Override
    public Issue createIssue(@RequestBody Issue issue) {
        IssueEntity issueEntity= new IssueEntity();
        BeanUtils.copyProperties(issue, issueEntity);
        issueRepository.save(issueEntity);
        return issue;
    }

    public long getIssueCount() {
        return issueRepository.count();
    }
}
