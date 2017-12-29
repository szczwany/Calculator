package com.szczwany.calculator.Project.controller;

import com.szczwany.calculator.Project.exception.ProjectNotFoundException;
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
import java.util.Optional;

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
        return ResponseEntity.ok().body(projectService.getProjects());
    }

    @PostMapping(value = "")
    public ResponseEntity<Long> addProject(@RequestBody @Valid Project newProject)
    {
        return Optional.ofNullable(projectService.addProject(newProject))
                .map(project ->
                        {
                            URI location = ServletUriComponentsBuilder
                                    .fromCurrentRequest().path("/{id}")
                                    .buildAndExpand(project.getId()).toUri();

                            return ResponseEntity.created(location).body(project.getId());
                        })
                .orElseGet(() ->
                        ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getProject(@PathVariable Long projectId)
    {
        return Optional.ofNullable(projectService.getProject(projectId))
                .map(project ->
                        ResponseEntity.ok().body(project))
                .orElseThrow(() ->
                        new ProjectNotFoundException(projectId));
    }

    @PutMapping(value = "/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable Long projectId, @RequestBody @Valid Project newProject)
    {
        return Optional.ofNullable(projectService.getProject(projectId))
                .map(project ->
                {
                    projectService.updateProject(projectId, newProject);

                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() ->
                        new ProjectNotFoundException(projectId));
    }

    @DeleteMapping(value = "/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId)
    {
        return Optional.ofNullable(projectService.getProject(projectId))
                .map(project ->
                {
                    projectService.deleteProject(projectId);

                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() ->
                        new ProjectNotFoundException(projectId));
    }
}
