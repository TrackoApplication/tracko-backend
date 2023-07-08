package com.app.tracko.controller;

import com.app.tracko.model.Priority;
import com.app.tracko.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v5")
public class PriorityController {

    @Autowired
    private final PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @PostMapping("/priorities")
    public Priority createPriority(@RequestBody Priority priority){
        return priorityService.createPriority(priority);
    }

    @GetMapping("/priorities")
    public List<Priority> getAllpriorities(){
        return priorityService.getAllpriorities();
    }
}
