package com.szczwany.calculator.Project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.szczwany.calculator.Helpers.ProjectFactory;
import com.szczwany.calculator.Project.controller.ProjectController;
import com.szczwany.calculator.Project.exception.ProjectNotFoundException;
import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.service.ProjectService;
import com.szczwany.calculator.Utils.GlobalControllerAdvice;
import com.szczwany.calculator.Utils.Globals;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ProjectController.class, secure = false)
public class ProjectControllerTests
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @MockBean
    private ProjectService projectService;

    @Before
    public void setup()
    {
        ProjectController projectController = new ProjectController(projectService);
        this.mockMvc = standaloneSetup(projectController)
                .setControllerAdvice(new GlobalControllerAdvice())
                .build();
    }

    @Test
    public void givenProject_whenGetProjects_thenWillReturnStatusOkAndProjectName() throws Exception
    {
        Project project = new Project();
        project.setName("name");

        List<Project> projects = Collections.singletonList(project);

        given(projectService.getProjects()).willReturn(projects);

        mockMvc.perform(get(Globals.PROJECTS_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(project.getName())));
    }

    @Test
    public void givenProjects_whenGetProjects_thenWillReturnStatusOkAndProjectsSize() throws Exception
    {
        Project project = new Project();
        project.setName("name");

        List<Project> projects = Arrays.asList(project, project, project);

        given(projectService.getProjects()).willReturn(projects);

        mockMvc.perform(get(Globals.PROJECTS_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void givenEmptyProjects_whenGetProjects_thenWillReturnStatusNoContent() throws Exception
    {
        given(projectService.getProjects()).willReturn(Lists.emptyList());

        mockMvc.perform(get(Globals.PROJECTS_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenProject_whenAddProject_thenExpectStatusCreated() throws Exception
    {
        Project project = ProjectFactory.createProject();

        doNothing().when(projectService).addProject(project);

        mockMvc.perform(post(Globals.PROJECTS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjToJson(project)))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenProject_whenAddProject_thenExpectStatusCreatedAndId() throws Exception
    {
        Project project = ProjectFactory.createProject();

        doNothing().when(projectService).addProject(project);

        mockMvc.perform(post(Globals.PROJECTS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjToJson(project)))
                .andExpect(status().isCreated())
                .andExpect(content().string(Long.toString(project.getId())));
    }

    @Test
    public void givenEmptyProject_whenAddProject_thenExpectStatusBadRequest() throws Exception
    {
        Project project = ProjectFactory.createEmptyProject();

        doNothing().when(projectService).addProject(project);

        mockMvc.perform(post(Globals.PROJECTS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjToJson(project)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenProjectId_whenGetProject_thenWillReturnProject() throws Exception
    {
        Project project = ProjectFactory.createProject();

        given(projectService.getProject(project.getId())).willReturn(project);

        mockMvc.perform(get(Globals.PROJECTS_PATH + Globals.PROJECT_ID, project.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(project.getId().intValue())))
                .andExpect(jsonPath("$.name", is(project.getName())))
                .andExpect(jsonPath("$.calculations", is(project.getCalculations())));
    }

    @Test
    public void givenProjectId_whenGetProject_thenWillReturnStatusNotFoundAndErrorMessage() throws Exception
    {
        Project project = ProjectFactory.createProject();

        when(projectService.getProject(project.getId())).thenThrow(new ProjectNotFoundException(project.getId()));

        mockMvc.perform(get(Globals.PROJECTS_PATH + Globals.PROJECT_ID, project.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage", is("Project '" + project.getId() + "' does not exist")));
    }

    @Test
    public void givenProject_whenUpdateProject_thenWillReturnStatusOk() throws Exception
    {
        Project project = ProjectFactory.createProject();

        when(projectService.getProject(project.getId())).thenReturn(project);
        doNothing().when(projectService).updateProject(project);

        mockMvc.perform(put(Globals.PROJECTS_PATH + Globals.PROJECT_ID, project.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjToJson(project)))
                .andExpect(status().isOk());

        // todo verify doesnt work ?
        //verify(projectService, times(1)).getProject(project.getId());
        //verify(projectService, times(1)).updateProject(project);
        //verifyNoMoreInteractions(projectService);
    }

    @Test
    public void givenProjectIdAndNullResponseBody_whenUpdateProject_thenWillReturnStatusBadRequest() throws Exception
    {
        Project project = ProjectFactory.createProject();

        when(projectService.getProject(project.getId())).thenReturn(project);
        doNothing().when(projectService).updateProject(project);

        mockMvc.perform(put(Globals.PROJECTS_PATH + Globals.PROJECT_ID, project.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjToJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenProjectId_whenUpdateProject_thenWillReturnStatusNotFound() throws Exception
    {
        Project project = ProjectFactory.createProject();

        when(projectService.getProject(project.getId())).thenThrow(new ProjectNotFoundException(project.getId()));

        mockMvc.perform(put(Globals.PROJECTS_PATH + Globals.PROJECT_ID, project.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjToJson(project)))
                .andExpect(status().isNotFound());

        verify(projectService, times(1)).getProject(project.getId());
        verifyNoMoreInteractions(projectService);
    }

    @Test
    public void givenProjectId_whenDeleteProject_thenWillReturnStatusOk() throws Exception
    {
        Project project = ProjectFactory.createProject();

        when(projectService.getProject(project.getId())).thenReturn(project);
        doNothing().when(projectService).deleteProject(project.getId());

        mockMvc.perform(delete(Globals.PROJECTS_PATH + Globals.PROJECT_ID, project.getId()))
                .andExpect(status().isOk());

        verify(projectService, times(1)).getProject(project.getId());
        verify(projectService, times(1)).deleteProject(project.getId());
        verifyNoMoreInteractions(projectService);
    }

    @Test
    public void givenProjectId_whenDeleteProject_thenWillReturnStatusNotFound() throws Exception
    {
        Project project = ProjectFactory.createProject();

        when(projectService.getProject(project.getId())).thenThrow(new ProjectNotFoundException(project.getId()));

        mockMvc.perform(delete(Globals.PROJECTS_PATH + Globals.PROJECT_ID, project.getId()))
                .andExpect(status().isNotFound());

        verify(projectService, times(1)).getProject(project.getId());
        verifyNoMoreInteractions(projectService);
    }

    private String convertObjToJson(Project project) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(project);
    }
}

