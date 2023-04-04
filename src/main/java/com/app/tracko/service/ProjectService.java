package com.app.tracko.service;


import com.app.tracko.model.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(Project project);

    List<Project> getAllProjects();

    boolean deleteProject(Long id);
}
