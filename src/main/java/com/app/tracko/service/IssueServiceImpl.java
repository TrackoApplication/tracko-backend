package com.app.tracko.service;

import com.app.tracko.entity.IssueEntity;
import com.app.tracko.model.Issue;
import com.app.tracko.repository.IssueRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
}
