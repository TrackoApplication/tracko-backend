package com.app.tracko.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Epic")

public class EpicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Name;
    private String Reporter;
    private String Description;
    private String Priority;
    private String Team;


}
