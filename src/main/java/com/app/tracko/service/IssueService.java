package com.app.tracko.service;

import com.app.tracko.model.Issue;

import java.util.List;

public interface IssueService {
    Issue createIssue(Issue issue);

    List<Issue> getAllIssues();

    boolean deleteIssue(Long issueId);

    Issue updateIssue(Long issueId, Issue issue);
}
