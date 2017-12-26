package com.szczwany.calculator.Project.service;

import com.szczwany.calculator.Project.exception.ProjectNotFoundException;
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
        return validateProject(projectId);
    }

    @Override
    public void updateProject(Long projectId, Project newProject)
    {
        validateProject(projectId);

        newProject.setId(projectId);
        iProjectRepository.save(newProject);
    }

    @Override
    public void deleteProject(Long projectId)
    {
        validateProject(projectId);

        iProjectRepository.delete(projectId);
    }

    @Override
    public Project validateProject(Long projectId)
    {
        Project tempProject = iProjectRepository.findOne(projectId);

        if (tempProject != null)
        {
            return tempProject;
        }
        else
        {
            throw new ProjectNotFoundException(projectId);
        }
    }
}
