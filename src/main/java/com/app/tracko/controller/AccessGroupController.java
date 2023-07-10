package com.app.tracko.controller;

import com.app.tracko.entity.*;
import com.app.tracko.model.AccessDto;
import com.app.tracko.model.AccessGroupDto;
import com.app.tracko.model.MembersDto;
import com.app.tracko.model.SystemUserDto;
import com.app.tracko.repository.*;
import com.app.tracko.service.AccessGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001","http://localhost:3002","http://localhost:3002"})
@RestController
@RequestMapping("/api/v1/accessgroups")
public class AccessGroupController {

    private final AccessGroupRepository accessGroupRepository;
    private final AccessRepository accessRepository;
    private final AccessGroupService accessGroupService;
    private final SystemUserRepository systemUserRepository;
    private final ProjectRepository projectRepository;
    private final ProjectGroupRepository projectGroupRepository;

    public AccessGroupController(AccessGroupRepository accessGroupRepository, AccessRepository accessRepository, AccessGroupService accessGroupService, SystemUserRepository systemUserRepository, ProjectRepository projectRepository, ProjectGroupRepository projectGroupRepository) {
        this.accessGroupRepository = accessGroupRepository;
        this.accessRepository = accessRepository;
        this.accessGroupService = accessGroupService;
        this.systemUserRepository = systemUserRepository;
        this.projectRepository = projectRepository;
        this.projectGroupRepository = projectGroupRepository;
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
    public List<AccessGroupDto> getAllAccessGroupDto(@RequestParam Long id){
        return accessGroupService.getAllAccessGroupDto(id);
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

    @GetMapping("/membersPerGroup/{id}")
    public List<MembersDto> getMembersOfGroup(@PathVariable Long id){
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

    @GetMapping("/membersPerProjectGroup")
    public List<MembersDto> membersPerProjectGroup(@RequestParam Long id1, @RequestParam Long id2){
        ProjectEntity p = projectRepository.findById(id1).get();
        ProjectGroupEntity pg = p.getProjectGroupEntity();
        List<MembersDto> membersDtos = new ArrayList<>();
        if(id2==1){
            SystemUserEntity s = pg.getProductOwner();
            MembersDto membersDto = new MembersDto();
            membersDto.setFirstName(s.getFirstName());
            membersDto.setSystemUserId(s.getSystemUserId());
            membersDtos.add(membersDto);
            return membersDtos;
        } else if (id2==2) {
            Set<SystemUserEntity> systemUserEntities = pg.getScrumMasters();
            for(SystemUserEntity a : systemUserEntities){
                MembersDto membersDto = new MembersDto();
                membersDto.setFirstName(a.getFirstName());
                membersDto.setSystemUserId(a.getSystemUserId());
                membersDtos.add(membersDto);
            }
            return membersDtos;
        }else{
            Set<SystemUserEntity> systemUserEntities = pg.getTeamMembers();
            for(SystemUserEntity a : systemUserEntities){
                MembersDto membersDto = new MembersDto();
                membersDto.setFirstName(a.getFirstName());
                membersDto.setSystemUserId(a.getSystemUserId());
                membersDtos.add(membersDto);
            }
            return membersDtos;
        }
    }

    @PutMapping("/userToGroup")
    public ResponseEntity<String> addUserToAccessGroup(@RequestParam Long id,
                                                       @RequestParam List<Long> id2) {
        try {
            AccessGroupEntity a = accessGroupRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Access Group not found"));

            List<SystemUserEntity> b = new ArrayList<>();
            for (Long x : id2) {
                SystemUserEntity y = systemUserRepository.findById(x)
                        .orElseThrow(() -> new NoSuchElementException("System User not found"));
                a.addUserToAccessGroup(y);
            }

            accessGroupRepository.save(a);

            return ResponseEntity.ok("Successfully updated Access Group");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the Access Group");
        }
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
    @GetMapping("/sm")
    public List<SystemUserDto> getScrumMasters(){
        AccessGroupEntity accessGroupEntity = accessGroupRepository.findById(2L).get();
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

    @GetMapping("/tm")
    public List<SystemUserDto> getTeamMembers(){
        AccessGroupEntity accessGroupEntity = accessGroupRepository.findById(3L).get();
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

    @GetMapping("/accessGroupMembers/{id}")
    public List<SystemUserDto> getAccessGroupMembers(@PathVariable Long id){
        AccessGroupEntity accessGroupEntity = accessGroupRepository.findById(id).get();
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
    public String poName(@PathVariable Long id) {
        try {
            ProjectEntity p = projectRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Project not found"));
            SystemUserEntity s = p.getProjectGroupEntity().getProductOwner();

            // Trigger lazy loading to fetch the SystemUserEntity object
            s.getFirstName();

            return s.getFirstName();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while retrieving the product owner name.";
        }
    }

    @PutMapping("/addGroupMembersToProject")
    public ResponseEntity<String> addScrumMasterToTheProject(@RequestParam Long id1, @RequestParam Long id2,  @RequestParam List<Long> id3) {
        try {
            ProjectEntity p = projectRepository.findById(id1)
                    .orElseThrow(() -> new NoSuchElementException("Project not found"));
            ProjectGroupEntity x = p.getProjectGroupEntity();

            List<SystemUserEntity> b = new ArrayList<>();
            for(Long y : id3) {
                SystemUserEntity s = systemUserRepository.findById(y)
                        .orElseThrow(() -> new NoSuchElementException("System User not found"));

                if (id2 == 2) {
                    x.addScrumMastersToProject(s);
                } else {
                    x.addTeamMembersToProject(s);
                }

            }

            projectGroupRepository.save(x);


            return ResponseEntity.ok("Successfully added Scrum Master to the project");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the project");
        }
    }

    @PutMapping("/teamMembersToProject")
    public ResponseEntity<String> addTeamMembersToTheProject(@RequestParam Long id1, @RequestParam Long id2) {
        try {
            ProjectEntity p = projectRepository.findById(id1)
                    .orElseThrow(() -> new NoSuchElementException("Project not found"));
            ProjectGroupEntity x = p.getProjectGroupEntity();
            SystemUserEntity s = systemUserRepository.findById(id2)
                    .orElseThrow(() -> new NoSuchElementException("System User not found"));

            x.addTeamMembersToProject(s);
            projectGroupRepository.save(x);

            return ResponseEntity.ok("Successfully added Scrum Master to the project");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the project");
        }
    }




}
