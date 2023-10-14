package com.app.tracko.controller;

import com.app.tracko.model.Duration;
import com.app.tracko.service.DurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v6")

public class DurationController {

    @Autowired
    private final DurationService durationService;

    public DurationController(DurationService durationService) {
        this.durationService = durationService;
    }

    @PostMapping("/durations")
    public Duration createDuration(@RequestBody Duration duration){
        return durationService.createDuration(duration);
    }

    @GetMapping("/durations")
    public List<Duration> getAlldurations(){
        return durationService.getAlldurations();
    }
}
