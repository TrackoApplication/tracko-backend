package com.app.tracko.controller;

import com.app.tracko.model.SprintIssue;
import com.app.tracko.service.SprintIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v3")

public class SprintIssueController {
    @Autowired
    private final SprintIssueService sprintissueservice;

    public SprintIssueController(SprintIssueService sprintissueservice) {
        this.sprintissueservice = sprintissueservice;
    }

    @PostMapping("/sprintissues")
    public SprintIssue createSprintIssue(@RequestBody SprintIssue sprintissue){
        return sprintissueservice.createSprintIssue(sprintissue);
    }

    @GetMapping("/sprintissues")
    public List<SprintIssue> getAllSprintIssues(){
        return sprintissueservice.getAllSprintIssues();
    }

    @DeleteMapping("/sprintissues/{SprintIssueId}")
    public ResponseEntity<Map<String,Boolean>> deleteSprintIssue(@PathVariable Long SprintIssueId) {
        boolean deleted = false;
        deleted = sprintissueservice.deleteSprintIssue(SprintIssueId);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }
}
