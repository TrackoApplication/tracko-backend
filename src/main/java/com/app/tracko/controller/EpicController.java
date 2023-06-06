package com.app.tracko.controller;


import com.app.tracko.entity.EpicEntity;
import com.app.tracko.model.Issue;
import com.app.tracko.service.EpicService;
import com.app.tracko.service.EpicServiceImpl;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/epics")
public class EpicController {
    private final EpicService epicService;

    public EpicController(EpicService epicService) {
        this.epicService = epicService;
    }

    @GetMapping("/count")
    public long getEpicCount(){
        return epicService.getIssueCount();
    }

    @PostMapping
    public EpicEntity createEpic(@RequestBody EpicEntity epic){
        return epicService.createEpic(epic);

    }




}
