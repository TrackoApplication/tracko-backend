package com.app.tracko.model;

import lombok.Data;

import java.util.Date;

@Data
public class Sprint {
    private long SprintId;
    private String SprintName;
    private String Duration;
    private Date StartDate;
    private Date EndDate;
    private String SprintGoal;
}
