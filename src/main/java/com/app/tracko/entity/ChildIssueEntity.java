package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "ChildIssue")
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ChildIssueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long childIssueId;
    private String childIssueName;
    private String Type;
    private String Created_date;
    private String Description;
    private int Story_points;
    private int Status;
    private String Summary;
    private String Assignee;
    private String Sprint;
    private Boolean ReqOfTest;
    private int DevEstimatedSP;
    private int TestEstimatedSP;
    private int TotalSP;
    private String Priority;
    private String Reporter;



}
