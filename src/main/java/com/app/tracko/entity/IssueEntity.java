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
    private String IssueType;
    private String Summary;
    private String Description;
    private String Assignee;

    @Nullable
    private Long SprintId;
    @Nullable
    private String SprintName;
    private String EpicName;
    private Boolean ReqOfTesting;
    private int SPDeveloping;
    private int SPTesting;
    private int TotalSP;
    private String Priority;
    private String Reporter;
}
