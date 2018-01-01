package com.szczwany.calculator.Project.controller;

import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.service.ProjectService;
import com.szczwany.calculator.Utils.Globals;
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
@RequestMapping(value = Globals.PROJECTS_PATH)
public class ProjectController
{
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService)
    {
        this.projectService = projectService;
    }

    @GetMapping(value = Globals.EMPTY_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Project>> getProjects()
    {
        Collection<Project> projects = projectService.getProjects();

        return projects.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(projects);
    }

    @PostMapping(value = Globals.EMPTY_PATH)
    public ResponseEntity<Long> addProject(@RequestBody @Valid Project project)
    {
        project.setId(null);
        projectService.addProject(project);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(Globals.PROJECT_ID)
                .buildAndExpand(project.getId())
                .toUri();

        return ResponseEntity.created(location).body(project.getId());
    }

    @GetMapping(value = Globals.PROJECT_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getProject(@PathVariable Long projectId)
    {
        Project project = projectService.getProject(projectId);

        return ResponseEntity.ok().body(project);
    }

    @PutMapping(value = Globals.PROJECT_ID)
    public ResponseEntity<?> updateProject(@PathVariable Long projectId, @RequestBody @Valid Project project)
    {
        projectService.getProject(projectId);
        project.setId(projectId);
        projectService.updateProject(project);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = Globals.PROJECT_ID)
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId)
    {
        projectService.getProject(projectId);
        projectService.deleteProject(projectId);

        return ResponseEntity.ok().build();
    }
}