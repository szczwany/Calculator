package com.szczwany.calculator.calculation.controller;

import com.szczwany.calculator.calculation.model.Calculation;
import com.szczwany.calculator.calculation.service.CalculationService;
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
        Project project = getProjectIfExists(projectId);
        Collection<Calculation> calculations = calculationService.getCalculationsByProject(project);

        return calculations.isEmpty() ? statusNoContent() : statusOkWithBody(calculations);
    }

    @PostMapping(value = EMPTY_PATH)
    public ResponseEntity<Long> addCalculation(@PathVariable Long projectId, @RequestBody @Valid Calculation calculation)
    {
        Project project = getProjectIfExists(projectId);
        setUpCalculationData(calculation, project, null);
        calculationService.addCalculation(calculation);

        return statusCreated(CALCULATIONS_PATH, calculation.getId());
    }

    @GetMapping(value = CALCULATION_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Calculation> getCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        Project project = getProjectIfExists(projectId);
        Calculation calculation = calculationService.getCalculation(project, calculationId);

        return statusOkWithBody(calculation);
    }

    @PutMapping(value = CALCULATION_ID_PATH)
    public ResponseEntity<?> updateCalculation(@PathVariable Long projectId, @PathVariable Long calculationId, @RequestBody @Valid Calculation calculation)
    {
        Project project = getProjectIfExists(projectId);
        calculationService.getCalculation(project, calculationId);
        setUpCalculationData(calculation, project, calculationId);
        calculationService.updateCalculation(calculation);

        return statusNoContent();
    }

    @DeleteMapping(value = CALCULATION_ID_PATH)
    public ResponseEntity<?> deleteCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        Project project = getProjectIfExists(projectId);
        calculationService.getCalculation(project, calculationId);
        calculationService.deleteCalculation(calculationId);

        return statusNoContent();
    }

    private Project getProjectIfExists(Long projectId)
    {
        return projectService.getProject(projectId);
    }

    private void setUpCalculationData(Calculation calculation, Project project, Long id)
    {
        calculation.setId(id);
        calculation.setProject(project);
        calculation.setResult(null);
        calculation.setUpdatedAt(null);
    }
}
