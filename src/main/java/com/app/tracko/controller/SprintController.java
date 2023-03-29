package com.app.tracko.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.app.tracko.model.Sprint;
import com.app.tracko.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
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

    @DeleteMapping("/sprints/{SprintId}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long SprintId) {
        boolean deleted = false;
        deleted = sprintService.deleteSprint(SprintId);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sprints/count")
    public long getSprintCount(){
        return sprintService.getSprintCount();
    }

}
