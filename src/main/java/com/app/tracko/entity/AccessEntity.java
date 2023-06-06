package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name="Access")
@AllArgsConstructor
@NoArgsConstructor
public class AccessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accessId;
    private String accessName;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<AccessGroupEntity> accessGroups = new ArrayList<>();

}
