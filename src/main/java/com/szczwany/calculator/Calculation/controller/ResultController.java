package com.szczwany.calculator.Calculation.controller;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Calculation.service.CalculationService;
import com.szczwany.calculator.Calculator.Calculator;
import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.service.ProjectService;
import com.szczwany.calculator.Utils.Globals;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

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

    @GetMapping(value = Globals.ALL_CALCULATIONS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Calculation>> getCalculations()
    {
        Collection<Calculation> calculations = calculationService.getCalculations();

        return calculations.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(calculations);
    }

    @GetMapping(value = Globals.ALL_CALCULATIONS_PATH + Globals.RESULT_PATH)
    public ResponseEntity<?> setResults()
    {
        Collection<Calculation> calculations = calculationService.getCalculations();

        return evaluateCalculations(calculations);
    }

    @GetMapping(value = Globals.PROJECTS_PATH + Globals.PROJECT_ID_PATH + Globals.RESULT_PATH)
    public ResponseEntity<?> setResultsByProject(@PathVariable Long projectId)
    {
        Project project = projectService.getProject(projectId);
        Collection<Calculation> calculations = calculationService.getCalculationsByProject(project);

        return evaluateCalculations(calculations);
    }

    @GetMapping(value = Globals.CALCULATIONS_PATH + Globals.CALCULATION_ID_PATH + Globals.RESULT_PATH)
    public ResponseEntity<?> setResultsByCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        Project project = projectService.getProject(projectId);
        Calculation calculation = calculationService.getCalculation(project, calculationId);

        return evaluateCalculations(Collections.singletonList(calculation));
    }

    private void getAndSaveCalculationResult(Calculation calculation)
    {
        Double result = Calculator.calculate(calculation.getExpression());

        if( result != null )
        {
            calculation.setResult(result);
            calculation.setUpdatedAt(new Date());
            calculationService.updateCalculation(calculation);
        }
    }

    private ResponseEntity<?> evaluateCalculations(Collection<Calculation> calculations)
    {
        for ( Calculation calculation : calculations )
        {
            getAndSaveCalculationResult(calculation);
        }

        return ResponseEntity.ok().build();
    }
}
