package com.app.tracko.controller;


import com.app.tracko.entity.PeopleEntity;
import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.service.PeopleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/peoples")
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @GetMapping("/count")
    public long getPeopleCount(){
        return peopleService.getPeopleCount();
    }

    @GetMapping
    public List<PeopleEntity> getAllPeoples(){
        return peopleService.getAllPeoples();
    }

    @PostMapping
    public PeopleEntity createPeople (@RequestBody PeopleEntity peopleEntity)
    {
        return peopleService.createPeople(peopleEntity);
    }

   
}
