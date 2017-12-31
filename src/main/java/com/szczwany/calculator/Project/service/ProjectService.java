package com.szczwany.calculator.Project.service;

import com.szczwany.calculator.Project.exception.ProjectNotFoundException;
import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService
{
    private IProjectRepository projectRepository;

    @Autowired
    public ProjectService(IProjectRepository projectRepository)
    {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getProjects()
    {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);

        return projects;
    }

    @Override
    public void addProject(Project project)
    {
        projectRepository.save(project);
    }

    @Override
    public Project getProject(Long projectId)
    {
        return Optional.ofNullable(projectRepository.findOne(projectId))
                .orElseThrow(() ->
                        new ProjectNotFoundException(projectId));
    }

    @Override
    public void updateProject(Project project)
    {
        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long projectId)
    {
        projectRepository.delete(projectId);
    }
}
