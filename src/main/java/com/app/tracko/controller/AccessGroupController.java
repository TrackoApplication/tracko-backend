package com.app.tracko.controller;

import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.repository.AccessGroupRepository;
import com.app.tracko.repository.SystemUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/accessgroups")
public class AccessGroupController {

    private final AccessGroupRepository accessGroupRepository;
    private final SystemUserRepository systemUserRepository;

    public AccessGroupController(AccessGroupRepository accessGroupRepository, SystemUserRepository systemUserRepository) {

        this.accessGroupRepository = accessGroupRepository;
        this.systemUserRepository = systemUserRepository;
    }

//    @GetMapping
//    public List<AccessGroupEntity> getAccessGroups(){
//        return accessGroupRepository.findAll();
//    }

    @PostMapping("/create")
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

    @GetMapping("/ view")
    public List<AccessGroupEntity> getAccessGroups(){
        return accessGroupRepository.findAll();
    }

    @PutMapping("/{grpid}/systemusers/{sysid}")
    AccessGroupEntity accessGroupAssignedUsers(
            @PathVariable Long grpid,
            @PathVariable Long sysid
    ){
        AccessGroupEntity accessGroupEntity = accessGroupRepository.findById(grpid).get();
        SystemUserEntity systemUserEntity = systemUserRepository.findById(sysid).get( );
        accessGroupEntity.assignAccessGroupToUser(systemUserEntity);
        return accessGroupRepository.save(accessGroupEntity);
    }
}
