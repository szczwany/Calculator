package com.szczwany.calculator.Calculation;

import com.szczwany.calculator.Calculation.controller.CalculationController;
import com.szczwany.calculator.Calculation.exception.CalculationNotFoundException;
import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Calculation.service.CalculationService;
import com.szczwany.calculator.helpers.CalculationFactory;
import com.szczwany.calculator.helpers.ProjectFactory;
import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.service.ProjectService;
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

import java.util.List;

import static com.szczwany.calculator.helpers.ObjectConverter.convertToJson;
import static com.szczwany.calculator.utils.Globals.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CalculationController.class, secure = false)
public class CalculationControllerTests
{
    private Project project;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private CalculationService calculationService;

    @Before
    public void setUp()
    {
        project = ProjectFactory.createProjectWithId();
        given(projectService.getProject(project.getId())).willReturn(project);
    }

    @Test
    public void givenCalculations_whenGetCalculationsByProject_thenWillReturnStatusOk() throws Exception
    {
        List<Calculation> calculations = CalculationFactory.createCalculations(project, NUM_OF_CALCULATIONS_TEST);
        given(calculationService.getCalculationsByProject(project)).willReturn(calculations);

        mockMvc.perform(get(CALCULATIONS_PATH, project.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenCalculations_whenGetCalculationsByProject_thenWillReturnStatusOkAndExpectSize() throws Exception
    {
        List<Calculation> calculations = CalculationFactory.createCalculations(project, NUM_OF_CALCULATIONS_TEST);
        given(calculationService.getCalculationsByProject(project)).willReturn(calculations);

        mockMvc.perform(get(CALCULATIONS_PATH, project.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(NUM_OF_CALCULATIONS_TEST)));
    }

    @Test
    public void givenEmptyCalculations_whenGetCalculationsByProject_thenWillReturnStatusNoContent() throws Exception
    {
        given(calculationService.getCalculationsByProject(project)).willReturn(Lists.emptyList());

        mockMvc.perform(get(CALCULATIONS_PATH, project.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenCalculation_whenAddCalculation_thenExpectStatusCreated() throws Exception
    {
        Calculation calculation = CalculationFactory.createCalculationWithProject(project);

        doNothing().when(calculationService).addCalculation(calculation);

        mockMvc.perform(post(CALCULATIONS_PATH, project.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(calculation)))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenCalculation_whenAddCalculation_thenExpectStatusBadRequest() throws Exception
    {
        Calculation calculation = CalculationFactory.createEmptyCalculation();

        doNothing().when(calculationService).addCalculation(calculation);

        mockMvc.perform(post(CALCULATIONS_PATH, project.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(calculation)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenCalculationId_whenGetCalculation_thenWillReturnCalculation() throws Exception
    {
        Calculation calculation = CalculationFactory.createCalculationWithProjectAndId(project);

        given(calculationService.getCalculation(project, calculation.getId())).willReturn(calculation);

        mockMvc.perform(get(CALCULATIONS_PATH + CALCULATION_ID_PATH, project.getId(), calculation.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(calculation.getId().intValue())))
                .andExpect(jsonPath("$.description", is(calculation.getDescription())))
                .andExpect(jsonPath("$.expression", is(calculation.getExpression())));
    }

    @Test
    public void givenCalculationId_whenGetCalculation_thenWillReturnStatusNotFoundAndErrorMessage() throws Exception
    {
        Calculation calculation = CalculationFactory.createCalculationWithProjectAndId(project);

        when(calculationService.getCalculation(project, calculation.getId())).thenThrow(new CalculationNotFoundException(calculation.getId()));

        mockMvc.perform(get(CALCULATIONS_PATH + CALCULATION_ID_PATH, project.getId(), calculation.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage", is("Calculation '" + calculation.getId() + "' does not exist")));
    }

    @Test
    public void givenCalculation_whenUpdateCalculation_thenWillReturnStatusNoContent() throws Exception
    {
        Calculation calculation = CalculationFactory.createCalculationWithProjectAndId(project);

        when(calculationService.getCalculation(project, calculation.getId())).thenReturn(calculation);
        doNothing().when(calculationService).updateCalculation(calculation);

        mockMvc.perform(put(CALCULATIONS_PATH + CALCULATION_ID_PATH, project.getId(), calculation.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(calculation)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenCalculation_whenUpdateCalculation_thenWillReturnStatusBadRequest() throws Exception
    {
        Calculation calculation = CalculationFactory.createCalculationWithProjectAndId(project);

        when(calculationService.getCalculation(project, calculation.getId())).thenReturn(calculation);
        doNothing().when(calculationService).updateCalculation(calculation);

        mockMvc.perform(put(CALCULATIONS_PATH + CALCULATION_ID_PATH, project.getId(), calculation.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenCalculationId_whenUpdateCalculation_thenWillReturnStatusNotFound() throws Exception
    {
        Calculation calculation = CalculationFactory.createCalculationWithProjectAndId(project);

        when(calculationService.getCalculation(project, calculation.getId())).thenThrow(new CalculationNotFoundException(calculation.getId()));

        mockMvc.perform(put(CALCULATIONS_PATH + CALCULATION_ID_PATH, project.getId(), calculation.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(calculation)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenCalculationId_whenDeleteCalculation_thenWillReturnStatusNoContent() throws Exception
    {
        Calculation calculation = CalculationFactory.createCalculationWithProjectAndId(project);

        when(calculationService.getCalculation(project, calculation.getId())).thenReturn(calculation);
        doNothing().when(calculationService).deleteCalculation(calculation.getId());

        mockMvc.perform(delete(CALCULATIONS_PATH + CALCULATION_ID_PATH, project.getId(), calculation.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenCalculationId_whenDeleteCalculation_thenWillReturnStatusNotFound() throws Exception
    {
        Calculation calculation = CalculationFactory.createCalculationWithProjectAndId(project);

        when(calculationService.getCalculation(project, calculation.getId())).thenThrow(new CalculationNotFoundException(calculation.getId()));

        mockMvc.perform(delete(CALCULATIONS_PATH + CALCULATION_ID_PATH, project.getId(), calculation.getId()))
                .andExpect(status().isNotFound());
    }
}
