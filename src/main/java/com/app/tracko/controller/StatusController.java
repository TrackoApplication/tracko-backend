package com.app.tracko.controller;

import com.app.tracko.model.Status;
import com.app.tracko.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v8")
public class StatusController {

    @Autowired
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping("/states")
    public Status createState(@RequestBody Status status){
        return statusService.createState(status);
    }

    @GetMapping("/states")
    public List<Status> getAllstates(){
        return statusService.getAllstates();
    }
}
