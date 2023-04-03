package com.app.tracko.service;
import com.app.tracko.entity.ProjectEntity;
import com.app.tracko.model.Project;
import com.app.tracko.repository.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;


    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createProject(Project project) {
        ProjectEntity projectEntity= new ProjectEntity();
        BeanUtils.copyProperties(project,projectEntity);
        projectRepository.save(projectEntity);
        return project;
    }
}
