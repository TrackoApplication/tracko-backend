package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_access_group")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAccessGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_user_id")
    private SystemUserEntity systemUserEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "access_group_id")
    private AccessGroupEntity accessGroupEntity;

}
