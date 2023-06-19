package com.app.tracko.service;

import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.model.AccessGroup;
import com.app.tracko.model.AccessGroupDto;
import com.app.tracko.model.Issue;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AccessGroupService {
    AccessGroupEntity createAccessGroup(AccessGroupEntity accessGroupEntity);

    List<AccessGroupEntity> getAllAccessGroups();

    List<AccessGroupDto> getAllAccessGroupDto();


}
