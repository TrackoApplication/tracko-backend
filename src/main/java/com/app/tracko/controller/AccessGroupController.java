package com.app.tracko.controller;

import com.app.tracko.entity.AccessEntity;
import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.entity.ProjectEntity;
import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.model.AccessDto;
import com.app.tracko.model.AccessGroupDto;
import com.app.tracko.model.MembersDto;
import com.app.tracko.model.SystemUserDto;
import com.app.tracko.repository.AccessGroupRepository;
import com.app.tracko.repository.AccessRepository;
import com.app.tracko.repository.ProjectRepository;
import com.app.tracko.repository.SystemUserRepository;
import com.app.tracko.service.AccessGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001","http://localhost:3002","http://localhost:3002"})
@RestController
@RequestMapping("/api/v1/accessgroups")
public class AccessGroupController {

    private final AccessGroupRepository accessGroupRepository;
    private final AccessRepository accessRepository;
    private final AccessGroupService accessGroupService;
    private final SystemUserRepository systemUserRepository;
    private final ProjectRepository projectRepository;

    public AccessGroupController(AccessGroupRepository accessGroupRepository, AccessRepository accessRepository, AccessGroupService accessGroupService, SystemUserRepository systemUserRepository, ProjectRepository projectRepository) {
        this.accessGroupRepository = accessGroupRepository;
        this.accessRepository = accessRepository;
        this.accessGroupService = accessGroupService;
        this.systemUserRepository = systemUserRepository;
        this.projectRepository = projectRepository;
    }


//    @PostMapping
//    public ResponseEntity<AccessGroupEntity> createAccessGroup(@RequestBody AccessGroupEntity accessGroupEntity) {
//        try {
//            accessGroupEntity = accessGroupService.createAccessGroup(accessGroupEntity);
//            List<AccessEntity> a = accessGroupEntity.getAccesses();
//            if (a != null) {
//                for (AccessEntity b : a) {
//                    AccessEntity accessEntity = b;
//                    accessRepository.save(accessEntity);
//                }
//            }
//            return ResponseEntity.ok(accessGroupEntity);
//        } catch (Exception e) {
//            // Handle the exception and return an appropriate response
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PutMapping
//    public ResponseEntity<AccessGroupEntity> updateAccessGroup(@RequestBody AccessGroupEntity accessGroupEntity,
//                                                               @RequestParam Long id,
//                                                               @RequestParam List<Long> id2) {
//        AccessGroupEntity a = accessGroupRepository.findById(id).get();
//        a.setAccessGroupName(accessGroupEntity.getAccessGroupName());
//        List<AccessEntity> listAccess = new ArrayList<>();
//        for(Long x : id2){
//            AccessEntity accessEntity = accessRepository.findById(x).get();
//            listAccess.add(accessEntity);
//        }
//        a.setAccesses(listAccess);
//        accessGroupRepository.save(a);
//        return ResponseEntity.ok(accessGroupEntity);
//
//    }
//
//

    @GetMapping("/allDto")
    public List<AccessGroupDto> getAllAccessGroupDto(){
        return accessGroupService.getAllAccessGroupDto();
    }

    @GetMapping("/one")
    public AccessGroupDto getAccessGroup(@RequestParam Long id) {
        AccessGroupEntity accessGroupEntity = accessGroupRepository.findById(id).get();
        AccessGroupDto accessGroupDto = new AccessGroupDto();
        accessGroupDto.setAccessGroupName(accessGroupEntity.getAccessGroupName());
        accessGroupDto.setAccessGroupId(accessGroupEntity.getAccessGroupId());
        accessGroupDto.setDescription(accessGroupEntity.getGroupDescription());
        return accessGroupDto;
    }

    @GetMapping("/")
    public ResponseEntity<List<AccessDto>> getAccessesForGroup(@RequestParam Long id) {
        Optional<AccessGroupEntity> optionalAccessGroup = accessGroupRepository.findById(id);
        if (optionalAccessGroup.isPresent()) {
            AccessGroupEntity accessGroupEntity = optionalAccessGroup.get();
            List<AccessDto> accessDtos = new ArrayList<>();
            for (AccessEntity accessEntity : accessGroupEntity.getAccesses()) {
                AccessDto accessDto = new AccessDto();
                accessDto.setAccessId(accessEntity.getAccessId());
                accessDto.setAccessName(accessEntity.getAccessName());
                accessDtos.add(accessDto);
            }
            return ResponseEntity.ok(accessDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AccessGroupEntity> createAccessGroup(@RequestBody AccessGroupEntity accessGroupEntity) {
        accessGroupEntity = accessGroupRepository.save(accessGroupEntity);
        return ResponseEntity.ok(accessGroupEntity);
    }

    @PutMapping("/addAccessToGroup")
    public ResponseEntity<String> UpdateAccessGroup(@RequestParam Long id,
                                                    @RequestBody List<Long> id2) {
        AccessGroupEntity a = accessGroupRepository.findById(id).get();
        List<AccessEntity> b = new ArrayList<>();
        for(Long x : id2) {
            AccessEntity y = accessRepository.findById(x).get();
            b.add(y);
        }
        a.setAccesses(b);
        accessGroupRepository.save(a);

        return ResponseEntity.ok("Successfully Updated AccessGroup");
    }

    @GetMapping()
    public AccessGroupEntity getAccessGroupById(@RequestParam Long id){
       return accessGroupRepository.findById(id).get();

    }
//    @GetMapping
//    public List<AccessGroupEntity> getAllAccessGroup(){
//        return accessGroupRepository.findAll();
//    }

    @GetMapping("/accessPerGroup")
    public List<AccessEntity> getAccessesOfGroup(@RequestParam Long id){
        AccessGroupEntity accessGroupEntity = accessGroupRepository.findById(id).get();
        List<AccessEntity> accessEntities = accessGroupEntity.getAccesses();
        return accessEntities;
    }

    @GetMapping("/membersPerGroup")
    public List<MembersDto> getMembersOfGroup(@RequestParam Long id){
        AccessGroupEntity accessGroupEntity = accessGroupRepository.findById(id).get();
        List<SystemUserEntity> systemUserEntities = accessGroupEntity.getSystemUserEntities();
        List<MembersDto> membersDtos = new ArrayList<>();
        for(SystemUserEntity a : systemUserEntities){
            MembersDto membersDto = new MembersDto();
            membersDto.setFirstName(a.getFirstName());
            membersDto.setSystemUserId(a.getSystemUserId());
            membersDtos.add(membersDto);
        }

        return membersDtos;
    }
//    @DeleteMapping("/access-groups/{groupId}/system-users/{userId}")
//    public void removeSystemUserFromAccessGroup(@PathVariable Long groupId, @PathVariable Long userId) {
//        // Retrieve the AccessGroupEntity by its ID from the database
//        AccessGroupEntity accessGroup = accessGroupService.findById(groupId);
//
//        if (accessGroup != null) {
//            // Retrieve the SystemUserEntity by its ID from the database
//            SystemUserEntity systemUser = systemUserService.findById(userId);
//
//            if (systemUser != null) {
//                // Remove the association between AccessGroupEntity and the specific SystemUserEntity
//                accessGroup.getSystemUserEntities().remove(systemUser);
//
//                // Update the AccessGroupEntity in the database
//                accessGroupService.save(accessGroup);
//            }
//        }
//    }


    @PutMapping("/userToGroup")
    public ResponseEntity<String> addUserToAccessGroup(@RequestParam Long id,
                                                        @RequestBody List<Long> id2) {
        AccessGroupEntity a = accessGroupRepository.findById(id).get();
        List<SystemUserEntity> b = new ArrayList<>();
        for(Long x : id2) {
            SystemUserEntity y = systemUserRepository.findById(x).get();
            a.addUserToAccessGroup(y);
        }
        accessGroupRepository.save(a);

        return ResponseEntity.ok("Successfully Updated AccessGroup");
    }

    @GetMapping("/po")
    public List<SystemUserDto> getProductOwner(){
        AccessGroupEntity accessGroupEntity = accessGroupRepository.findById(1L).get();
        List<SystemUserEntity> systemUserEntities = accessGroupEntity.getSystemUserEntities();
        List<SystemUserDto> systemUserDtos = new ArrayList<>();
        for(SystemUserEntity s : systemUserEntities){
            SystemUserDto systemUserDto = new SystemUserDto();
            systemUserDto.setFirstName(s.getFirstName());
            systemUserDto.setRole(s.getRole());
            systemUserDto.setSystemUserId(s.getSystemUserId());
            systemUserDto.setEmailId(s.getEmailId());
            systemUserDtos.add(systemUserDto);
        }
        return systemUserDtos;
    }

    @GetMapping("/poName/{id}")
    public String poName(@PathVariable Long id){
        ProjectEntity p = projectRepository.findById(id).get();
        return p.getProjectLead();
    }


    }
