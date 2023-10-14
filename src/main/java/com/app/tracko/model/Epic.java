package com.app.tracko.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Epic {
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

    public Epic(long id, String epicName, String project, String epicDescription, String assignee, String priority, String reporter, String storyPoints, String targetEnd, String targetStart, String team) {
    }
}