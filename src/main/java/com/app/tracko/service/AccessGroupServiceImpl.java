package com.app.tracko.service;

import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.model.AccessGroupDto;
import com.app.tracko.repository.AccessGroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccessGroupServiceImpl implements AccessGroupService {

    private final AccessGroupRepository accessGroupRepository;

    public AccessGroupServiceImpl(AccessGroupRepository accessGroupRepository) {
        this.accessGroupRepository = accessGroupRepository;
    }

    @Override
    public AccessGroupEntity createAccessGroup(AccessGroupEntity accessGroupEntity) {

        return accessGroupRepository.save(accessGroupEntity);
    }

    @Override
    public List<AccessGroupEntity> getAllAccessGroups() {
        return accessGroupRepository.findAll();
    }

    @Override
    public List<AccessGroupDto> getAllAccessGroupDto(){
        List<AccessGroupEntity> accessGroupEntities = accessGroupRepository.findAll();
        List<AccessGroupDto> accessGroupDtos = new ArrayList<>();
        for(AccessGroupEntity a : accessGroupEntities){
            AccessGroupDto accessGroupDto = new AccessGroupDto();
            accessGroupDto.setAccessGroupId(a.getAccessGroupId());
            accessGroupDto.setAccessGroupName(a.getAccessGroupName());
            accessGroupDto.setDescription(a.getGroupDescription());
            accessGroupDto.setNoOfMembers((long) a.getSystemUserEntities().size());
            accessGroupDtos.add(accessGroupDto);
        }
        return accessGroupDtos;
    }
}
