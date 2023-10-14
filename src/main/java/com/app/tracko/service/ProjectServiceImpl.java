package com.app.tracko.service;
import com.app.tracko.entity.ProjectEntity;
import com.app.tracko.model.Project;
import com.app.tracko.repository.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
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

    @Override
    public List<Project> getAllProjects() {
        List<ProjectEntity> projectEntities=projectRepository.findAll();
        List<Project> projects=projectEntities
                .stream()
                .map(pro->new Project(
                        pro.getId(),
                        pro.getProjectName(),
                        pro.getImageURL(),
                        pro.getDescription(),
                        pro.getClient(),
                        pro.getProjectLead()))
                .collect(Collectors.toList());
        return projects;
    }

    @Override
    public boolean deleteProject(Long id) {
        ProjectEntity project = projectRepository.findById(id).get();
        projectRepository.delete(project);
        return true;
    }


    @Override
    public Project updateProject(Long id, Project project) {
        ProjectEntity projectEntity = projectRepository.findById(id).get();
        projectEntity.setProjectName(project.getProjectName());
        projectEntity.setImageURL(project.getImageURL());
        projectEntity.setDescription(project.getDescription());
        projectEntity.setClient(project.getClient());

        projectRepository.save(projectEntity);
        return project;
    }
}