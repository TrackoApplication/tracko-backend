package com.app.tracko.controller;

import com.app.tracko.entity.ChildIssueEntity;
import com.app.tracko.repository.ChildIssueRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/auth/childissue")
public class ChildIssueController {

    private final ChildIssueRepository childIssueRepository;

    public ChildIssueController(ChildIssueRepository childIssueRepository) {
        this.childIssueRepository = childIssueRepository;
    }

    @GetMapping
    public List<ChildIssueEntity> getChildIssue(){
       return childIssueRepository.findAll();
    }

    @PostMapping
    public ChildIssueEntity createChildIssue(@RequestBody ChildIssueEntity childIssueEntity){
        return childIssueRepository.save(childIssueEntity);
    }


}
