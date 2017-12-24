package com.szczwany.calculator.Project.controller;

import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/projects")
public class ProjectController
{
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService)
    {
        this.projectService = projectService;
    }

    @GetMapping(value = "")
    public List<Project> getProjects()
    {
        return projectService.getProjects();
    }

    @PostMapping(value = "")
    public Long addProject(@RequestBody @Valid Project project)
    {
        projectService.addProject(project);

        return project.getId();
    }

    @GetMapping(value = "/{projectId}")
    public Project getProject(@PathVariable Long projectId)
    {
        return projectService.getProject(projectId);
    }

    @PutMapping(value = "/{projectId}")
    public void updateProject(@PathVariable Long projectId, @RequestBody @Valid Project project)
    {
        projectService.updateProject(projectId, project);
    }

    @DeleteMapping(value = "/{projectId}")
    public void deleteProject(@PathVariable Long projectId)
    {
        projectService.deleteProject(projectId);
    }
}
