package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "Epic")
@Builder
@AllArgsConstructor
@Entity
public class EpicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long epicId;
    private String epicName;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "epicEntity" )
    private List<ChildIssueEntity> childIssueEntity ;

    public EpicEntity() {

    }
}
