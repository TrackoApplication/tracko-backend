package com.app.tracko.controller;

import com.app.tracko.model.Issue;
import com.app.tracko.model.Sprint;
import com.app.tracko.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2")
public class SprintController {
    @Autowired
    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @PostMapping("/sprints")
    public Sprint createSprint(@RequestBody Sprint sprint){
        return sprintService.createSprint(sprint);
    }

    @GetMapping("/sprints")
    public List<Sprint> getAllSprints(){
        return sprintService.getAllSprints();
    }
}
