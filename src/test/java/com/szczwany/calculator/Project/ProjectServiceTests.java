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
    public void whenValidProjectsInDb_thenReturnProjectsList()
    {
        final int numberOfProjects = 10;
        List<Project> projects = ProjectFactory.createProjects(numberOfProjects);
        when(projectRepository.findAll()).thenReturn(projects);

        assertThat(projectService.getProjects()).hasSize(numberOfProjects);
    }

    @Test
    public void whenNoProjects_thenReturnProjectsEmptyList()
    {
        when(projectRepository.findAll()).thenReturn(Lists.emptyList());

        assertThat(projectService.getProjects()).hasSize(0);
    }

    @Test
    public void whenValidProjectId_thenProjectIsFoundWithName()
    {
        Project project = ProjectFactory.createProject();
        when(projectRepository.findOne(project.getId())).thenReturn(project);

        assertThat(projectService.getProject(project.getId()).getName()).isEqualTo(project.getName());
    }

    @Test(expected = ProjectNotFoundException.class)
    public void whenInvalidProjectId_thenProjectNotFoundException()
    {
        final Long id = 1L;
        when(projectRepository.findOne(id)).thenReturn(null);

        projectService.getProject(id);
    }

    @Test
    public void whenValidProject_thenAddProjectToDb()
    {
        Project project = ProjectFactory.createProject();
        when(projectRepository.save(project)).thenReturn(project);

        projectService.addProject(project);
    }

    @Test
    public void whenValidProject_thenUpdateProject()
    {
        Project project = ProjectFactory.createProject();
        when(projectRepository.save(project)).thenReturn(project);

        projectService.updateProject(project);
    }

    @Test
    public void whenValidProjectId_thenDeleteProject()
    {
        final Long id = 1L;
        doNothing().when(projectRepository).delete(id);

        projectService.deleteProject(id);
    }
}
