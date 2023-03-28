package com.app.tracko.model;

import com.app.tracko.entity.SystemUserEntity;

import java.util.Set;

public class AccessGroup {
    private long accessGroupId;
    private String accessGroupName;
    private Set<SystemUserEntity> systemUserEntities ;

    public AccessGroup(long accessGroupId, String accessGroupName, Set<SystemUserEntity> systemUserEntities) {

    }
}
