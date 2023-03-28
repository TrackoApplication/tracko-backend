package com.app.tracko.controller;

import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.entity.UserAccessGroupEntity;
import com.app.tracko.repository.UserAccessGroupRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/user")
public class UserAccessGroupController {

    private final UserAccessGroupRepository userAccessGroupRepository;

    public UserAccessGroupController(UserAccessGroupRepository userAccessGroupRepository) {
        this.userAccessGroupRepository = userAccessGroupRepository;
    }

    @PostMapping
    public UserAccessGroupEntity createUserAccessGroup(@RequestBody  UserAccessGroupEntity userAccessGroupEntity){
        userAccessGroupRepository.save(userAccessGroupEntity);
        return userAccessGroupEntity;
    }

    @GetMapping
    public List<UserAccessGroupEntity> getUserAccessGroupDetails(){
        List<UserAccessGroupEntity> userAccessGroupEntities
                = userAccessGroupRepository.findAll();
        return userAccessGroupEntities;
    }
}
