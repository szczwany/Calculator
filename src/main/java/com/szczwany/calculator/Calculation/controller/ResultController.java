package com.szczwany.calculator.Calculation.controller;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Calculation.model.ResultThread;
import com.szczwany.calculator.Calculation.service.CalculationService;
import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.service.ProjectService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.szczwany.calculator.utils.Globals.*;
import static com.szczwany.calculator.utils.Response.*;

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

        return calculations.isEmpty() ? statusNoContent() : statusOkWithBody(calculations);
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

    private ResponseEntity<?> evaluateCalculations(Collection<Calculation> calculations)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (Calculation calculation : calculations)
        {
            ResultThread resultWorker = new ResultThread(calculation, calculationService);
            executorService.submit(resultWorker);
        }

        executorService.shutdown();

        while (!executorService.isTerminated())
        {

        }

        return statusNoContent();
    }
}
