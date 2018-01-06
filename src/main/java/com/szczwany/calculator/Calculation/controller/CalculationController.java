package com.szczwany.calculator.Calculation.controller;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Calculation.service.CalculationService;
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
@RequestMapping(value = CALCULATIONS_PATH)
public class CalculationController
{
    private ProjectService projectService;
    private CalculationService calculationService;

    @Autowired
    public CalculationController(ProjectService projectService, CalculationService calculationService)
    {
        this.projectService = projectService;
        this.calculationService = calculationService;
    }

    @GetMapping(value = EMPTY_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCalculationsByProject(@PathVariable Long projectId)
    {
        Project project = getProject(projectId);
        Collection<Calculation> calculations = calculationService.getCalculationsByProject(project);

        return calculations.isEmpty() ? noContent() : okBody(calculations);
    }

    @PostMapping(value = EMPTY_PATH)
    public ResponseEntity<Long> addCalculation(@PathVariable Long projectId, @RequestBody @Valid Calculation calculation)
    {
        Project project = getProject(projectId);
        setCalculationData(calculation, project, null);
        calculationService.addCalculation(calculation);

        return created(CALCULATIONS_PATH, calculation.getId());
    }

    @GetMapping(value = CALCULATION_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Calculation> getCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        Project project = getProject(projectId);
        Calculation calculation = calculationService.getCalculation(project, calculationId);

        return okBody(calculation);
    }

    @PutMapping(value = CALCULATION_ID_PATH)
    public ResponseEntity<?> updateCalculation(@PathVariable Long projectId, @PathVariable Long calculationId, @RequestBody @Valid Calculation calculation)
    {
        Project project = getProject(projectId);
        calculationService.getCalculation(project, calculationId);
        setCalculationData(calculation, project, calculationId);
        calculationService.updateCalculation(calculation);

        return noContent();
    }

    @DeleteMapping(value = CALCULATION_ID_PATH)
    public ResponseEntity<?> deleteCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        Project project = getProject(projectId);
        calculationService.getCalculation(project, calculationId);
        calculationService.deleteCalculation(calculationId);

        return noContent();
    }

    private Project getProject(@PathVariable Long projectId)
    {
        return projectService.getProject(projectId);
    }

    private void setCalculationData(Calculation calculation, Project project, Long id)
    {
        calculation.setId(id);
        calculation.setProject(project);
        calculation.setResult(null);
        calculation.setUpdatedAt(null);
    }
}
