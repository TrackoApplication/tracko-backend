package com.app.tracko.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private long id;
    private String projectName;
    private String imageURL;
    private String description;
    private String client;
    private String projectLead;
}
