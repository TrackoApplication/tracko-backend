package com.app.tracko.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Issuetypes")

public class IssuetypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IssuetypeId;
    private String IssuetypeName;

}
