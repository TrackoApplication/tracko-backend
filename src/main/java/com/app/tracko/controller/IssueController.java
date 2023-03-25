package com.app.tracko.controller;

import com.app.tracko.model.Issue;
import com.app.tracko.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")

public class IssueController {

    @Autowired
    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping("/issues")
    public Issue createIssue(@RequestBody Issue issue){
         return issueService.createIssue(issue);
    }

    @GetMapping("/issues")
    public List<Issue> getAllIssues(){
        return issueService.getAllIssues();
    }
}
