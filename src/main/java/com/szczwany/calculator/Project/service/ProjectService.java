package com.szczwany.calculator.Project.service;

import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService implements IProjectService
{
    private IProjectRepository iProjectRepository;

    @Autowired
    public ProjectService(IProjectRepository iProjectRepository)
    {
        this.iProjectRepository = iProjectRepository;
    }

    @Override
    public List<Project> getProjects()
    {
        List<Project> projects = new ArrayList<>();

        iProjectRepository.findAll().forEach(projects::add);

        return projects;
    }

    @Override
    public void addProject(Project project)
    {
        iProjectRepository.save(project);
    }

    @Override
    public Project getProject(Long projectId)
    {
        return iProjectRepository.findOne(projectId);
    }

    @Override
    public void updateProject(Long projectId, Project project)
    {
        project.setId(projectId);
        iProjectRepository.save(project);
    }

    @Override
    public void deleteProject(Long projectId)
    {
        iProjectRepository.delete(projectId);
    }
}
