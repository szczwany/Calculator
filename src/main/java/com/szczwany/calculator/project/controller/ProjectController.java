package com.szczwany.calculator.project.controller;

import com.szczwany.calculator.project.model.Project;
import com.szczwany.calculator.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static com.szczwany.calculator.utils.Globals.*;
import static com.szczwany.calculator.utils.Response.*;

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

        return projects.isEmpty() ? statusNoContent() : statusOkWithBody(projects);
    }

    @PostMapping(value = EMPTY_PATH)
    public ResponseEntity<Long> addProject(@RequestBody @Valid Project project)
    {
        setUpProjectData(project, null);
        projectService.addProject(project);

        return statusCreated(PROJECTS_PATH, project.getId());
    }

    @GetMapping(value = PROJECT_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getProject(@PathVariable Long projectId)
    {
        Project project = projectService.getProject(projectId);

        return statusOkWithBody(project);
    }

    @PutMapping(value = PROJECT_ID_PATH)
    public ResponseEntity<?> updateProject(@PathVariable Long projectId, @RequestBody @Valid Project project)
    {
        projectService.getProject(projectId);
        setUpProjectData(project, projectId);
        projectService.updateProject(project);

        return statusNoContent();
    }

    @DeleteMapping(value = PROJECT_ID_PATH)
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId)
    {
        projectService.getProject(projectId);
        projectService.deleteProject(projectId);

        return statusNoContent();
    }

    private void setUpProjectData(Project project, Long id)
    {
        project.setId(id);
    }
}