package com.app.tracko.service;

import com.app.tracko.entity.Status;
import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.model.Issue;
import com.app.tracko.entity.IssueEntity;
import com.app.tracko.model.SystemUser;
import com.app.tracko.repository.IssueRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.app.tracko.entity.IssueEntity;
import com.app.tracko.model.Issue;
import com.app.tracko.repository.IssueRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
                        issue.getReporter(),
                        issue.getStatus() )).collect(Collectors.toList()
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

//    @Override
//    public Issue getStatus(String status) {
//        IssueEntity issueEntity
//                = issueRepository.findByStatus(status);
//        Issue issue = new Issue();
//        BeanUtils.copyProperties(issueEntity, issue);
//        return issue;
//    }

//    @Override
//    public List<Issue> getAssignee(String assignee) {
//        IssueEntity issueEntities
//                = issueRepository.findByAssignee(assignee);
//        List<Issue> issues= issueEntities.stream().
//                map(issue-> new Issue(
//                        issue.getIssueId(),
//                        issue.getProjectName(),
//                        issue.getIssueType(),
//                        issue.getSummary(),
//                        issue.getDescription(),
//                        issue.getAssignee(),
//                        issue.getSprintName(),
//                        issue.getEpicName(),
//                        issue.getReqOfTesting(),
//                        issue.getSPDeveloping(),
//                        issue.getSPTesting(),
//                        issue.getTotalSP(),
//                        issue.getPriority(),
//                        issue.getReporter(),
//                        issue.getStatus() )).collect(Collectors.toList()
//                );
//        return issues;
//
//    }



}
