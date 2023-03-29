package com.app.tracko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToMany
    @JoinTable(
            name = "user_access_group",
            joinColumns = @JoinColumn(name = "access_group_Id"),
            inverseJoinColumns = @JoinColumn(name = "system_user_id")
    )
    private Set<SystemUserEntity> accessGroupUsers = new HashSet<>();


    public void assignAccessGroupToUser(SystemUserEntity systemUserEntity) {
        accessGroupUsers.add(systemUserEntity);
    }
}
