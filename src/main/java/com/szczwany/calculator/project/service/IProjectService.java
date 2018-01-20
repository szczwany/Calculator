package com.szczwany.calculator.project.service;

import com.szczwany.calculator.project.model.Project;

import java.util.List;

public interface IProjectService
{
    List<Project> getProjects();
    void addProject(Project project);
    Project getProject(Long projectId);
    void updateProject(Project project);
    void deleteProject(Long projectId);
}
