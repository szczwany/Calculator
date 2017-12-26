package com.szczwany.calculator.Calculation.controller;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Calculation.service.CalculationService;
import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/projects/{projectId}/calculations")
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

    @GetMapping(value = "")
    public List<Calculation> getCalculations(@PathVariable Long projectId)
    {
        Project project = projectService.getProject(projectId);

        return calculationService.getCalculations(project);
    }

    @PostMapping(value = "")
    public Long addCalculation(@PathVariable Long projectId, @RequestBody @Valid Calculation calculation)
    {
        Project project = projectService.getProject(projectId);

        calculationService.addCalculation(project, calculation);

        return calculation.getId();
    }

    @GetMapping(value = "/{calculationId}")
    public Calculation getCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        Project project = projectService.getProject(projectId);

        return calculationService.getCalculation(project, calculationId);
    }

    @PutMapping(value = "/{calculationId}")
    public void updateCalculation(@PathVariable Long projectId, @PathVariable Long calculationId, @RequestBody @Valid Calculation calculation)
    {
        Project project = projectService.getProject(projectId);

        calculationService.updateCalculation(project, calculationId, calculation);
    }

    @DeleteMapping(value = "/{calculationId}")
    public void updateCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        Project project = projectService.getProject(projectId);

        calculationService.deleteCalculation(project, calculationId);
    }

    @GetMapping(value = "/{calculationId}/calculate")
    public String calculate(@PathVariable Long calculationId)
    {
        return calculationService.calculate(calculationId);
    }
}
