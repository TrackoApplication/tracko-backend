package com.app.tracko.service;
import com.app.tracko.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;


    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
}
