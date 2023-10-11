package com.app.tracko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Issue {
    private long IssueId;
    private String ProjectName;
    private String issuetypeName;
    private String Summary;
    private String Description;
    private String Assignee;
    private String EpicName;
    private Boolean ReqOfTesting;
    private int SPDeveloping;
    private int SPTesting;
    private int TotalSP;
    private String Priority;
    private String Reporter;
    private String Status;
    private String SprintName;
    private Long sprintId;
}
