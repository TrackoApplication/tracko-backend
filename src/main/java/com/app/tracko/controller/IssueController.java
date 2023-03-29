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
@RequestMapping("/api/v1/issues")
public class IssueController {
    private final IssueService issueService;
    private final IssueRepository issueRepository;

    public IssueController(IssueService issueService, IssueRepository issueRepository) {
        this.issueService = issueService;
        this.issueRepository = issueRepository;
    }


    @PostMapping
    public Issue createIssue(@RequestBody Issue issue){
        return issueService.createIssue(issue);

    }

    @GetMapping
    public List<Issue> getAllIssues(){
        return issueService.getAllIssues();
    }

    @GetMapping("/count")
    public long getIssueCount(){
        return issueService.getIssueCount();
    }

    @GetMapping("/status/{Status}")
    public long getStatus(@PathVariable String Status){
        return issueRepository.findByStatus(Status);
    }

    @GetMapping("/highrisk")
    public long getHighRisk(){
        return issueRepository.findByTotalSPHigh();
    }

    @GetMapping("/mediumrisk")
    public long getMediumRisk(){
        return issueRepository.findByTotalSPMedium();
    }

    @GetMapping("/lowrisk")
    public long getLowRisk(){
        return issueRepository.findByTotalSPLow();
    }


//    @GetMapping("/issues/{Assignee}")
//    public ResponseEntity<Issue> getAssignee(@PathVariable String Assignee){
//        Issue issue = issueService.getAssignee(Assignee);
//        return ResponseEntity.ok(issue);
//    }

        @GetMapping("/{Assignee}")
        public List<IssueEntity> getAssignee(@PathVariable String Assignee){
            return issueRepository.findByAssignee(Assignee);
        }


}
