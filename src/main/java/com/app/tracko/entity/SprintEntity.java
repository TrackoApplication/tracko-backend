package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Sprints")

public class SprintEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long SprintId;
    private String SprintName;
    private String Duration;
    private Date StartDate;
    private Date EndDate;
    private String SprintGoal;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(name = "sprint_issue_group",
            joinColumns = {@JoinColumn(name = "issue_fk")},
            inverseJoinColumns = {@JoinColumn(name = "sprint_fk")}
    )
    private List<IssueEntity> issues;

}
