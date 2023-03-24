package com.app.tracko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sprint {
    private long SprintId;
    private String SprintName;
    private String Duration;
    private Date StartDate;
    private Date EndDate;
    private String SprintGoal;
}
