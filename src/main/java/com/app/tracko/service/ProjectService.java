package com.app.tracko.service;

import com.app.tracko.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    Project createProject(Project project);

    List<Project> getAllProjects();

    boolean deleteProject(Long id);

    Project updateProject(Long id, Project project);
}
