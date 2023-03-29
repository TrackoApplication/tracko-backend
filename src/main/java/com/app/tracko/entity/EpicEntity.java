package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Table(name = "Epic")
@Builder
@AllArgsConstructor
@Entity
public class EpicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long EpicId;
    private String EpicName;

    public EpicEntity() {

    }
}
