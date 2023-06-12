package com.app.tracko.controller;

import com.app.tracko.entity.AccessEntity;
import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.repository.AccessGroupRepository;
import com.app.tracko.repository.AccessRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/access")
public class AccessController {

    private final AccessRepository accessRepository;
    private final AccessGroupRepository accessGroupRepository;

    public AccessController(AccessRepository accessRepository, AccessGroupRepository accessGroupRepository) {
        this.accessRepository = accessRepository;
        this.accessGroupRepository = accessGroupRepository;
    }

    @PostMapping
    public ResponseEntity<String> createAccess(@RequestBody AccessEntity accessEntity) {
        accessRepository.save(accessEntity);
        return new ResponseEntity<>("Access created successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public List<AccessEntity> getAllAccess(){
        return accessRepository.findAll();
    }

//    @PutMapping
//    public ResponseEntity<String> updateAccess(@RequestParam Long id1,
//                                               @RequestParam List<Long> id2){
//        AccessEntity a = accessRepository.findById(id1).get();
//        List<AccessGroupEntity> b = new ArrayList<>();
//        for(Long x : id2){
//            AccessGroupEntity y = accessGroupRepository.findById(x).get();
//            b.add(y);
//        }
//        a.setAccessGroups(b);
//        accessRepository.save(a);
//
//        return ResponseEntity.ok("succesfully updated Access");
//
//    }





}
