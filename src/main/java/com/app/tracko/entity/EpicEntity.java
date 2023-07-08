package com.app.tracko.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Epic")

public class EpicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String project;
    private String epicName;
    private String epicSummary;
    private String reporter;
    private String epicDescription;
    private String priority;
    private String assignee;
    private String team;
    private String targetStart;
    private String targetEnd;
    private String storyPoints;

}