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

    @ManyToOne(optional = false,
    fetch = FetchType.LAZY)
    @JoinColumn(
            name = "epic_fk",
            referencedColumnName = "epicId"
    )
    private EpicEntity epicEntity;



}
