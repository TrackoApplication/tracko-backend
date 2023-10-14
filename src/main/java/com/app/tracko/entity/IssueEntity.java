package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.Nullable;

@Entity
@Data
@Table(name = "Issues")
public class IssueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "sprint_Id")
    private SprintEntity sprint;

    @ManyToOne
    @JoinColumn(name = "epic_Id")
    private EpicEntity epic;
}
