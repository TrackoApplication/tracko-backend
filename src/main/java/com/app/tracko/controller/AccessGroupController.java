package com.app.tracko.controller;

import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.model.AccessGroup;
import com.app.tracko.repository.AccessGroupRepository;
import com.app.tracko.service.AccessGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/accessgroups")
public class AccessGroupController {

    private final AccessGroupRepository accessGroupRepository;

    public AccessGroupController(AccessGroupRepository accessGroupRepository) {

        this.accessGroupRepository = accessGroupRepository;
    }

//    @GetMapping
//    public List<AccessGroupEntity> getAccessGroups(){
//        return accessGroupRepository.findAll();
//    }

    @PostMapping
    public AccessGroupEntity createAccessGroup(@RequestBody  AccessGroupEntity accessGroupEntity){
        accessGroupRepository.save(accessGroupEntity);
        return accessGroupEntity;
    }

//    @GetMapping
//    public List<AccessGroup> getAccessGroups(){
//        List<AccessGroupEntity> accessGroupEntities
//                = accessGroupRepository.findAll();
//        List<AccessGroup> accessGroups= accessGroupEntities.stream().
//                map(accGrp-> new AccessGroup(
//                        accGrp.getAccessGroupId(),
//                        accGrp.getAccessGroupName(),
//                        accGrp.getSystemUserEntities() )).collect(Collectors.toList()
//                );
//        return accessGroups;
//    }

    @GetMapping
    public List<AccessGroupEntity> getAccessGroups(){
        List<AccessGroupEntity> accessGroupEntities
                = accessGroupRepository.findAll();
        return accessGroupEntities;
    }
}
