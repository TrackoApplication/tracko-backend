package com.app.tracko.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="Projects")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String projectName;
    private String imageURL;
    private String description;
    private String client;
    private String projectLead;


}