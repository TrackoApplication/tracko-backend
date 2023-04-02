package com.app.tracko.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Epic {
    private long id;
    private String Name;
    private String Reporter;
    private String Description;
    private String Priority;
    private String Team;
}
