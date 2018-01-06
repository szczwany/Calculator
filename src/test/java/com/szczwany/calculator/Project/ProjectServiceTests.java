package com.szczwany.calculator.Project;

import com.szczwany.calculator.Helpers.ProjectFactory;
import com.szczwany.calculator.Project.exception.ProjectNotFoundException;
import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.repository.IProjectRepository;
import com.szczwany.calculator.Project.service.IProjectService;
import com.szczwany.calculator.Project.service.ProjectService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.szczwany.calculator.Utils.Globals.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ProjectServiceTests
{
    private IProjectRepository projectRepository;
    private IProjectService projectService;

    @Before
    public void setUp()
    {
        projectRepository = mock(IProjectRepository.class);
        projectService = new ProjectService(projectRepository);
    }

    @Test
    public void whenProjectsInDatabase_thenReturnProjects()
    {
        List<Project> projects = ProjectFactory.createProjects(NUM_OF_PROJECTS_TEST);
        when(projectRepository.findAll()).thenReturn(projects);

        assertThat(projectService.getProjects()).hasSize(NUM_OF_PROJECTS_TEST);
    }

    @Test
    public void whenNoProjectsInDatabase_thenReturnProjectsEmptyList()
    {
        when(projectRepository.findAll()).thenReturn(Lists.emptyList());

        assertThat(projectService.getProjects()).hasSize(0);
    }

    @Test
    public void whenValidProjectId_thenProjectIsFoundWithName()
    {
        Project project = ProjectFactory.createProjectWithId();
        when(projectRepository.findOne(project.getId())).thenReturn(project);

        assertThat(projectService.getProject(project.getId()).getName()).isEqualTo(project.getName());
    }

    @Test(expected = ProjectNotFoundException.class)
    public void whenInvalidProjectId_thenProjectNotFoundException()
    {
        when(projectRepository.findOne(ID_TEST)).thenReturn(null);

        projectService.getProject(ID_TEST);
    }

    @Test
    public void whenValidProject_thenAddProjectToDatabase()
    {
        Project project = ProjectFactory.createProjectWithId();
        when(projectRepository.save(project)).thenReturn(project);

        projectService.addProject(project);
    }

    @Test
    public void whenValidProject_thenUpdateProject()
    {
        Project project = ProjectFactory.createProjectWithId();
        when(projectRepository.save(project)).thenReturn(project);

        projectService.updateProject(project);
    }

    @Test
    public void whenValidProjectId_thenDeleteProject()
    {
        doNothing().when(projectRepository).delete(ID_TEST);

        projectService.deleteProject(ID_TEST);
    }
}
