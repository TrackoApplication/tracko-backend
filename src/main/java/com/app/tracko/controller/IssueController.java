package com.app.tracko.controller;

import com.app.tracko.model.Issue;
import com.app.tracko.repository.IssueRepository;
import com.app.tracko.repository.SprintRepository;
import com.app.tracko.service.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    private final IssueService issueService;
    private final SprintRepository sprintRepository;
    private final IssueRepository issueRepository;

    public IssueController(IssueService issueService, SprintRepository sprintRepository, IssueRepository issueRepository) {
        this.issueService = issueService;
        this.sprintRepository = sprintRepository;
        this.issueRepository = issueRepository;
    }

    @PostMapping
    public Issue createIssue(@RequestBody Issue issue) {
        return issueService.createIssue(issue);
    }

    @GetMapping
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }

    @DeleteMapping("/{IssueId}")
    public ResponseEntity<Map<String, Boolean>> deleteIssue(@PathVariable Long IssueId) {
        boolean deleted = false;
        deleted = issueService.deleteIssue(IssueId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/issues/{IssueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long IssueId) {
        Issue issue = null;
        issue = issueService.getIssueById(IssueId);
        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{IssueId}")
    public ResponseEntity<Issue> updateIssue(@PathVariable Long IssueId, @RequestBody Issue issue) {
        issue = issueService.updateIssue(IssueId, issue);
        return ResponseEntity.ok(issue);
    }

    //Newly added
    @PutMapping("/{IssueId}/sprint")
    public ResponseEntity<Issue> updateIssueSprint(@PathVariable Long IssueId, @RequestBody Issue updatedIssue) {
        try {
            Issue issue = issueService.updateIssueSprint(IssueId, updatedIssue);
            return ResponseEntity.ok(issue);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}

//    @PutMapping
//    public ResponseEntity<String> UpdateIssuesToSprint(@RequestParam Long issueId, @RequestParam Long sprintId) {
//        SprintEntity sprintEntity = sprintRepository.findById(sprintId).get();
//        List<IssueEntity> issueEntities = new ArrayList<>();
//        IssueEntity issue = issueRepository.findById(issueId).get();
//        issueEntities.add(issue);
//        sprintEntity.setIssues(issueEntities);
//        sprintRepository.save(sprintEntity);
//
//        return ResponseEntity.ok("Successfully Updated sprint");
//    }
