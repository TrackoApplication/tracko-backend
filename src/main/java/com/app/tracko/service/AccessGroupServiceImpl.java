package com.app.tracko.service;

import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.entity.ProjectEntity;
import com.app.tracko.entity.ProjectGroupEntity;
import com.app.tracko.model.AccessGroupDto;
import com.app.tracko.repository.AccessGroupRepository;
import com.app.tracko.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccessGroupServiceImpl implements AccessGroupService {

    private final AccessGroupRepository accessGroupRepository;
    private final ProjectRepository projectRepository;

    public AccessGroupServiceImpl(AccessGroupRepository accessGroupRepository, ProjectRepository projectRepository) {
        this.accessGroupRepository = accessGroupRepository;
        this.projectRepository = projectRepository;
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
    public List<AccessGroupDto> getAllAccessGroupDto(Long id){
        ProjectEntity p = projectRepository.findById(id).get();
        ProjectGroupEntity pg = p.getProjectGroupEntity();
        List<AccessGroupEntity> accessGroupEntities = accessGroupRepository.findAll();
        List<AccessGroupDto> accessGroupDtos = new ArrayList<>();
        for(AccessGroupEntity a : accessGroupEntities){
            AccessGroupDto accessGroupDto = new AccessGroupDto();
            accessGroupDto.setAccessGroupId(a.getAccessGroupId());
            accessGroupDto.setAccessGroupName(a.getAccessGroupName());
            accessGroupDto.setDescription(a.getGroupDescription());
            if(a.getAccessGroupId()==1){
                accessGroupDto.setNoOfMembers(1L);
            } else if (a.getAccessGroupId()==2) {
                accessGroupDto.setNoOfMembers((long) pg.getScrumMasters().size());
            }else {
                accessGroupDto.setNoOfMembers((long) pg.getTeamMembers().size());
            }

            accessGroupDtos.add(accessGroupDto);
        }
        return accessGroupDtos;
    }
}
