package com.app.tracko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SprintIssue {
    private long SprintIssueId;
    private String ProjectName;
    private String IssueType;
    private String Summary;
    private String Description;
    private String Assignee;
    private String SprintName;
    private String EpicName;
    private Boolean ReqOfTesting;
    private int SPDeveloping;
    private int SPTesting;
    private int TotalSP;
    private String Priority;
    private String Reporter;
}
