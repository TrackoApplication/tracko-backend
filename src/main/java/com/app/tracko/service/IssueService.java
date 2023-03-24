package com.app.tracko.service;

import com.app.tracko.model.Issue;

import java.util.List;

public interface IssueService {
    List<Issue> getAllIssues();

    Issue createIssue(Issue issue);

    long getIssueCount();
}
