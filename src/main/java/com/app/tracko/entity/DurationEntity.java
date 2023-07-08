package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Durations")

public class DurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long DurationId;
    private String Duration;

}
