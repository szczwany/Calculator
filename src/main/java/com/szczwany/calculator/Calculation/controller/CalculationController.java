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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@Controller
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

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Calculation>> getCalculations(@PathVariable Long projectId)
    {
        Project project = projectService.getProject(projectId);
        Collection<Calculation> calculations = calculationService.getCalculations(project);

        if( calculations.isEmpty() )
        {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(calculations);
    }

    @PostMapping(value = "")
    public ResponseEntity<Long> addCalculation(@PathVariable Long projectId, @RequestBody @Valid Calculation calculation)
    {
        Project project = projectService.getProject(projectId);
        Calculation newCalculation = calculationService.addCalculation(project, calculation);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{calculationId}")
                .buildAndExpand(newCalculation.getId()).toUri();

        return ResponseEntity.created(location).body(newCalculation.getId());
    }

    @GetMapping(value = "/{calculationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Calculation> getCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        Project project = projectService.getProject(projectId);
        Calculation calculation = calculationService.getCalculation(project, calculationId);

        return ResponseEntity.ok().body(calculation);
    }

    @PutMapping(value = "/{calculationId}")
    public ResponseEntity<?> updateCalculation(@PathVariable Long projectId, @PathVariable Long calculationId, @RequestBody @Valid Calculation calculation)
    {
        Project project = projectService.getProject(projectId);
        calculationService.updateCalculation(project, calculationId, calculation);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{calculationId}")
    public ResponseEntity<?> updateCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        Project project = projectService.getProject(projectId);
        calculationService.deleteCalculation(project, calculationId);

        return ResponseEntity.ok().build();
    }
}
