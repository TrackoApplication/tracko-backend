package com.app.tracko.controller;

import com.app.tracko.entity.IssueEntity;
import com.app.tracko.model.Issue;
import com.app.tracko.repository.IssueRepository;
import com.app.tracko.service.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class IssueController {
    private final IssueService issueService;
    private final IssueRepository issueRepository;

    public IssueController(IssueService issueService, IssueRepository issueRepository) {
        this.issueService = issueService;
        this.issueRepository = issueRepository;
    }


    @PostMapping("/issues")
    public Issue createIssue(@RequestBody Issue issue){
        return issueService.createIssue(issue);

    }

    @GetMapping("/issues")
    public List<Issue> getAllIssues(){
        return issueService.getAllIssues();
    }

    @GetMapping("/issues/count")
    public long getIssueCount(){
        return issueService.getIssueCount();
    }

    @GetMapping("/issues/{status}")
    public ResponseEntity<Issue> getStatus(@PathVariable String status){
        Issue issue = issueService.getStatus(status);
//        .orElseThrow(()-> new ConfigDataResourceNotFoundException("systemusers not exist with id :"+ id));
        return ResponseEntity.ok(issue);
    }

//    @GetMapping("/issues/{Assignee}")
//    public ResponseEntity<Issue> getAssignee(@PathVariable String Assignee){
//        Issue issue = issueService.getAssignee(Assignee);
//        return ResponseEntity.ok(issue);
//    }

        @GetMapping("/issuesc/{Assignee}")
        public List<IssueEntity> getAssignee(@PathVariable String Assignee){
            return (List<IssueEntity>) issueRepository.findByAssignee(Assignee);
        }


}
