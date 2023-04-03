package com.app.tracko.controller;
import com.app.tracko.model.Project;
import com.app.tracko.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://localhost:3000" )
@RestController
@RequestMapping("/api/v1/")

public class ProjectController {

    @Autowired
    private final ProjectService projectService;


    public ProjectController(ProjectService projectService)
    {
        this.projectService = projectService;
    }

    @PostMapping("/project")
    public Project createProject(@RequestBody Project project){
        return projectService.createProject(project);
    }

    @GetMapping("/project")
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }













}
