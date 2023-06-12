package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="AccessGroup",
        uniqueConstraints = @UniqueConstraint(
        name = "accessGroupName_unique",
        columnNames = "accessGroupName"
))
@AllArgsConstructor
@NoArgsConstructor
public class AccessGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accessGroupId;
    private String accessGroupName;
    private String groupDescription;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(name = "access_access_group",
            joinColumns = {@JoinColumn(name = "access_group_fk")},
            inverseJoinColumns = {@JoinColumn(name = "access_fk")}
    )
    private List<AccessEntity> accesses ;

    public void addAccessToAccessGroup(AccessEntity accessEntity){
        if (accessEntity!= null){
            accesses.add(accessEntity);
        }
    }




}
