package com.app.tracko.service;

import com.app.tracko.model.Issue;
import com.app.tracko.model.Sprint;

import java.util.List;

public interface IssueService {
    Issue createIssue(Issue issue);

    List<Issue> getAllIssues();

    boolean deleteIssue(Long issueId);

    Issue getIssueById(Long issueId);

    Issue updateIssue(Long issueId, Issue issue);

    //Newly added
    Issue updateIssueSprint(Long issueId, Issue updatedIssue);


}
