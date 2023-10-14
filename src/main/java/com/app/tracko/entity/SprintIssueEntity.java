package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "SprintIssues")

public class SprintIssueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
