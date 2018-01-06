package com.szczwany.calculator.Calculation.controller;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Calculation.service.CalculationService;
import com.szczwany.calculator.Calculator.Calculator;
import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.service.ProjectService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Collections;

import static com.szczwany.calculator.Utils.Globals.*;
import static com.szczwany.calculator.Utils.Response.*;

@Controller
public class ResultController
{
    private CalculationService calculationService;
    private ProjectService projectService;

    public ResultController(CalculationService calculationService, ProjectService projectService)
    {
        this.calculationService = calculationService;
        this.projectService = projectService;
    }

    @GetMapping(value = ALL_CALCULATIONS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCalculations()
    {
        Collection<Calculation> calculations = calculationService.getCalculations();

        return calculations.isEmpty() ? noContent() : okBody(calculations);
    }

    @GetMapping(value = ALL_CALCULATIONS_PATH + RESULT_PATH)
    public ResponseEntity<?> setResults()
    {
        Collection<Calculation> calculations = calculationService.getCalculations();

        return evaluateCalculations(calculations);
    }

    @GetMapping(value = PROJECTS_PATH + PROJECT_ID_PATH + RESULT_PATH)
    public ResponseEntity<?> setResultsByProject(@PathVariable Long projectId)
    {
        Project project = projectService.getProject(projectId);
        Collection<Calculation> calculations = calculationService.getCalculationsByProject(project);

        return evaluateCalculations(calculations);
    }

    @GetMapping(value = CALCULATIONS_PATH + CALCULATION_ID_PATH + RESULT_PATH)
    public ResponseEntity<?> setResultsByCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        Project project = projectService.getProject(projectId);
        Calculation calculation = calculationService.getCalculation(project, calculationId);

        return evaluateCalculations(Collections.singletonList(calculation));
    }

    private void getAndSaveCalculationResult(Calculation calculation)
    {
        Double result = Calculator.calculate(calculation.getExpression());

        if(result != null)
        {
            calculation.setResultAndUpdatedAt(result);
            calculationService.updateCalculation(calculation);
        }
    }

    private ResponseEntity<?> evaluateCalculations(Collection<Calculation> calculations)
    {
        for (Calculation calculation : calculations)
        {
            getAndSaveCalculationResult(calculation);
        }

        return ok();
    }
}
