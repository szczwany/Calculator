package com.szczwany.calculator.Project.controller;

import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static com.szczwany.calculator.Utils.Globals.*;
import static com.szczwany.calculator.Utils.Response.*;

@Controller
@RequestMapping(value = PROJECTS_PATH)
public class ProjectController
{
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService)
    {
        this.projectService = projectService;
    }

    @GetMapping(value = EMPTY_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProjects()
    {
        Collection<Project> projects = projectService.getProjects();

        return projects.isEmpty() ? noContent() : okBody(projects);
    }

    @PostMapping(value = EMPTY_PATH)
    public ResponseEntity<Long> addProject(@RequestBody @Valid Project project)
    {
        prepareProjectData(project, null);
        projectService.addProject(project);

        return created(PROJECTS_PATH, project.getId());
    }

    @GetMapping(value = PROJECT_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getProject(@PathVariable Long projectId)
    {
        Project project = projectService.getProject(projectId);

        return okBody(project);
    }

    @PutMapping(value = PROJECT_ID_PATH)
    public ResponseEntity<?> updateProject(@PathVariable Long projectId, @RequestBody @Valid Project project)
    {
        projectService.getProject(projectId);
        prepareProjectData(project, projectId);
        projectService.updateProject(project);

        return ok();
    }

    @DeleteMapping(value = PROJECT_ID_PATH)
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId)
    {
        projectService.getProject(projectId);
        projectService.deleteProject(projectId);

        return ok();
    }

    private void prepareProjectData(@RequestBody @Valid Project project, Long o)
    {
        project.setId(o);
    }
}