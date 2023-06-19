package com.app.tracko.controller;


import com.app.tracko.entity.EpicEntity;
import com.app.tracko.repository.EpicRepository;
import com.app.tracko.service.EpicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001","http://localhost:3002","http://localhost:3002"})
@RestController
@RequestMapping("/api/v1/auth/epics")
public class EpicController {
    private final EpicService epicService;
    private final EpicRepository epicRepository;

    public EpicController(EpicService epicService, EpicRepository epicRepository) {
        this.epicService = epicService;
        this.epicRepository = epicRepository;
    }

    @GetMapping("/count")
    public long getEpicCount(){
        return epicService.getIssueCount();
    }

    @PostMapping
    public EpicEntity createEpic(@RequestBody EpicEntity epic){
        return epicService.createEpic(epic);
    }

    @GetMapping
    public List<EpicEntity> getAllEpics(){
        return epicRepository.findAll();
    }




}
