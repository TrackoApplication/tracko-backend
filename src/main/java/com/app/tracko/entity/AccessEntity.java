package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Access")
public class AccessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long AccessId;
    private String AccessName;
}
