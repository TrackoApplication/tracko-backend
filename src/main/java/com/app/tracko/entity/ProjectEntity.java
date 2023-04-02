package com.app.tracko.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="Project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ProjectName;
    private String Description;
    private String Client;


}
