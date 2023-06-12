package com.app.tracko.controller;

import com.app.tracko.entity.AccessEntity;
import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.model.AccessDto;
import com.app.tracko.model.AccessGroupDto;
import com.app.tracko.repository.AccessGroupRepository;
import com.app.tracko.repository.AccessRepository;
import com.app.tracko.service.AccessGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/accessgroups")
public class AccessGroupController {

    private final AccessGroupRepository accessGroupRepository;
    private final AccessRepository accessRepository;
    private final AccessGroupService accessGroupService;

    public AccessGroupController(AccessGroupRepository accessGroupRepository, AccessRepository accessRepository, AccessGroupService accessGroupService) {
        this.accessGroupRepository = accessGroupRepository;
        this.accessRepository = accessRepository;
        this.accessGroupService = accessGroupService;
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
    @GetMapping("/dto")
    public List<AccessGroupDto> getAllAccessGroups() {
        List<AccessGroupEntity> accessGroupEntity = accessGroupService.getAllAccessGroups();
        List<AccessGroupDto> accessGroupDtos = new ArrayList<>();
        for (AccessGroupEntity a : accessGroupEntity){
            AccessGroupDto accessGroupDto = new AccessGroupDto();
            accessGroupDto.setAccessGroupName(a.getAccessGroupName());
            accessGroupDto.setId(a.getAccessGroupId());
            accessGroupDto.setDescription(a.getGroupDescription());
            accessGroupDtos.add(accessGroupDto);
        }
        return accessGroupDtos;
    }
    @GetMapping("/one")
    public AccessGroupDto getAccessGroup(@RequestParam Long id) {
        AccessGroupEntity accessGroupEntity = accessGroupRepository.findById(id).get();
        AccessGroupDto accessGroupDto = new AccessGroupDto();
        accessGroupDto.setAccessGroupName(accessGroupEntity.getAccessGroupName());
        accessGroupDto.setId(accessGroupEntity.getAccessGroupId());
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

    @PutMapping
    public ResponseEntity<String> UpdateAccessGroup(@RequestParam Long id,
                                                    @RequestParam List<Long> id2) {
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
    @GetMapping("/all")
    public List<AccessGroupEntity> getAllAccessGroup(){
        return accessGroupRepository.findAll();
    }

    @GetMapping("/perGroup")
    public List<AccessEntity> getAaccessesOfGroup(@RequestParam Long id){
        AccessGroupEntity accessGroupEntity = accessGroupRepository.findById(id).get();
        List<AccessEntity> accessEntities = accessGroupEntity.getAccesses();
        return accessEntities;
    }


    }
