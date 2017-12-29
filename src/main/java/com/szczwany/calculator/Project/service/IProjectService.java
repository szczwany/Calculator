package com.szczwany.calculator.Project.service;

import com.szczwany.calculator.Project.model.Project;

import java.util.List;

public interface IProjectService
{
    List<Project> getProjects();
    Project addProject(Project project);
    Project getProject(Long projectId);
    void updateProject(Long projectId, Project project);
    void deleteProject(Long projectId);
}
