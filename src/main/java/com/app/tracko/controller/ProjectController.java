package com.app.tracko.controller;
import com.app.tracko.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")

public class ProjectController {

    @Autowired
    private final ProjectService projectService;


    public ProjectController(ProjectService projectService)
    {
        this.projectService = projectService;
    }
}
