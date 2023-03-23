package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="AccessGroup")
public class AccessGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long AccessGroupId;
    private String AccessGroupName;




}
