package com.app.tracko.controller;

import com.app.tracko.model.Issue;
import com.app.tracko.model.Sprint;
import com.app.tracko.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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

    @DeleteMapping("/sprints/{SprintId}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long SprintId) {
        boolean deleted = false;
        deleted = sprintService.deleteSprint(SprintId);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sprints/{SprintId}")
    public ResponseEntity<Sprint> getSprintById(@PathVariable Long SprintId) {
        Sprint sprint = null;
        sprint = sprintService.getSprintById(SprintId);
        return ResponseEntity.ok(sprint);
    }

    @PutMapping("/sprints/{SprintId}")
    public ResponseEntity<Sprint> updateSprint(@PathVariable Long SprintId, @RequestBody Sprint sprint) {
        sprint = sprintService.updateSprint(SprintId, sprint);
        return ResponseEntity.ok(sprint);
    }

    //Newly added
//    @PutMapping("/sprints/{sprintId}/updateIssue")
//    public ResponseEntity<Sprint> updateIssueSprint(
//            @PathVariable Long sprintId,
//            @RequestBody Issue updatedIssue
//    ) {
//        try {
//            Sprint sprint = sprintService.updateIssueSprint(sprintId, updatedIssue);
//            return ResponseEntity.ok(sprint);
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }

}
