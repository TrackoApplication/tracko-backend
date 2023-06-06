package com.app.tracko.service;

import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.model.Issue;
import com.app.tracko.repository.AccessGroupRepository;
import org.springframework.stereotype.Service;

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
}
