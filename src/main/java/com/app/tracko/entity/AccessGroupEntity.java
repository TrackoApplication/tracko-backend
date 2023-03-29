package com.app.tracko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.app.tracko.entity.SystemUserEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="AccessGroup")
@AllArgsConstructor
@NoArgsConstructor
public class AccessGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accessGroupId;
    private String accessGroupName;

    @JsonIgnore
    @ManyToMany(mappedBy = "accessGroups",
            fetch = FetchType.LAZY
    )
    private Set<SystemUserEntity> systemUserEntities ;


}
