package com.szczwany.calculator.Project.controller;

import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping(value = "/v1/projects")
public class ProjectController
{
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService)
    {
        this.projectService = projectService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Project>> getProjects()
    {
        Collection<Project> projects = projectService.getProjects();

        return projects.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(projects);
    }

    @PostMapping(value = "")
    public ResponseEntity<Long> addProject(@RequestBody @Valid Project project)
    {
        projectService.addProject(project);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{projectId}")
                .buildAndExpand(project.getId())
                .toUri();

        return ResponseEntity.created(location).body(project.getId());
    }

    @GetMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getProject(@PathVariable Long projectId)
    {
        Project project = projectService.getProject(projectId);

        return ResponseEntity.ok().body(project);
    }

    @PutMapping(value = "/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable Long projectId, @RequestBody @Valid Project project)
    {
        projectService.getProject(projectId);
        project.setId(projectId);
        projectService.updateProject(project);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId)
    {
        projectService.getProject(projectId);
        projectService.deleteProject(projectId);

        return ResponseEntity.ok().build();
    }
}