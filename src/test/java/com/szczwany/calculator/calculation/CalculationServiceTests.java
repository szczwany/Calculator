package com.szczwany.calculator.calculation;

import com.szczwany.calculator.calculation.exception.CalculationNotFoundException;
import com.szczwany.calculator.calculation.model.Calculation;
import com.szczwany.calculator.calculation.repository.ICalculationRepository;
import com.szczwany.calculator.calculation.service.CalculationService;
import com.szczwany.calculator.calculation.service.ICalculationService;
import com.szczwany.calculator.helpers.CalculationFactory;
import com.szczwany.calculator.helpers.ProjectFactory;
import com.szczwany.calculator.project.model.Project;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.szczwany.calculator.utils.Globals.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CalculationServiceTests
{
    private Project project;

    private ICalculationRepository calculationRepository;
    private ICalculationService calculationService;

    @Before
    public void setUp()
    {
        calculationRepository = mock(ICalculationRepository.class);
        calculationService = new CalculationService(calculationRepository);

        project = ProjectFactory.createProject();
    }

    @Test
    public void whenValidCalculationsInDatabase_thenReturnCalculations()
    {
        List<Calculation> calculations = CalculationFactory.createCalculations(project, NUM_OF_CALCULATIONS_TEST);
        when(calculationRepository.findAll()).thenReturn(calculations);

        assertThat(calculationService.getCalculations()).hasSize(NUM_OF_CALCULATIONS_TEST);
    }

    @Test
    public void whenNoCalculationsInDatabase_thenReturnEmptyList()
    {
        when(calculationRepository.findAll()).thenReturn(Lists.emptyList());

        assertThat(calculationService.getCalculations()).isEmpty();
    }

    @Test
    public void whenValidCalculationsByProjectInDatabase_thenReturnCalculationsByProject()
    {
        List<Calculation> calculations = CalculationFactory.createCalculations(project, NUM_OF_CALCULATIONS_TEST);
        when(calculationRepository.findByProject(project)).thenReturn(calculations);

        assertThat(calculationService.getCalculationsByProject(project)).hasSize(NUM_OF_CALCULATIONS_TEST);
    }

    @Test
    public void whenNoCalculationsByProjectInDatabase_thenReturnCalculationsByProjectEmptyList()
    {
        when(calculationRepository.findByProject(project)).thenReturn(Lists.emptyList());

        assertThat(calculationService.getCalculationsByProject(project)).isEmpty();
    }

    @Test
    public void whenValidCalculationId_thenCalculationIsFoundWithDescriptionAndExpression()
    {
        Calculation calculation = CalculationFactory.createCalculationWithProject(project);
        when(calculationRepository.findByProjectAndId(project, calculation.getId())).thenReturn(calculation);

        assertThat(calculationService.getCalculation(project, calculation.getId()).getDescription()).isEqualTo(calculation.getDescription());
        assertThat(calculationService.getCalculation(project, calculation.getId()).getExpression()).isEqualTo(calculation.getExpression());
    }

    @Test(expected = CalculationNotFoundException.class)
    public void whenInvalidCalculationId_thenCalculationIsFoundWithDescriptionAndExpression()
    {
        when(calculationRepository.findByProjectAndId(project, TEST_ID)).thenReturn(null);

        calculationService.getCalculation(project, TEST_ID);
    }

    @Test
    public void whenValidCalculation_thenAddCalculationToDatabase()
    {
        Calculation calculation = CalculationFactory.createCalculationWithProject(project);
        when(calculationRepository.save(calculation)).thenReturn(calculation);

        calculationService.addCalculation(calculation);
    }

    @Test
    public void whenValidCalculation_thenUpdateCalculation()
    {
        Calculation calculation = CalculationFactory.createCalculationWithProjectAndId(project);
        when(calculationRepository.findByProjectAndId(project, calculation.getId())).thenReturn(calculation);
        when(calculationService.getCalculation(project, calculation.getId())).thenReturn(calculation);
        when(calculationRepository.save(calculation)).thenReturn(calculation);

        calculationService.updateCalculation(calculation);
    }

    @Test
    public void whenValidCalculationId_thenDeleteCalculation()
    {
        Calculation calculation = CalculationFactory.createCalculationWithProjectAndId(project);
        when(calculationRepository.findByProjectAndId(project, calculation.getId())).thenReturn(calculation);
        when(calculationService.getCalculation(project, calculation.getId())).thenReturn(calculation);
        doNothing().when(calculationRepository).delete(calculation.getId());

        calculationService.deleteCalculation(calculation.getId());
    }
}
