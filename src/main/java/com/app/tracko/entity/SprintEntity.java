package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

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

}
