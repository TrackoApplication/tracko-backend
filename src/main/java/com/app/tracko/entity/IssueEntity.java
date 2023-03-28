package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "Issues")
@Builder
@AllArgsConstructor
public class IssueEntity {
    @Id
    @SequenceGenerator(
            name = "issue_sequence",
            sequenceName = "issue_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "issue_sequence"
    )
    private long IssueId;
    private String ProjectName;
    private String IssueType;
    private String Summary;
    private String Description;
    @Column(name = "Assignee")
    private String Assignee;
    private String SprintName;
    private String EpicName;
    private Boolean ReqOfTesting;
    private int SPDeveloping;
    private int SPTesting;
    private int TotalSP;
    private String Priority;
    private String Reporter;
    @Column(name = "Status")
    private String Status;

    public IssueEntity() {

    }

}