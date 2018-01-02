package com.szczwany.calculator.Calculation.controller;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Calculation.service.CalculationService;
import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.service.ProjectService;
import com.szczwany.calculator.Utils.Globals;
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
@RequestMapping(value = Globals.CALCULATIONS_PATH)
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

    @GetMapping(value = Globals.EMPTY_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Calculation>> getCalculationsByProject(@PathVariable Long projectId)
    {
        Project project = projectService.getProject(projectId);
        Collection<Calculation> calculations = calculationService.getCalculationsByProject(project);

        return calculations.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(calculations);
    }

    @PostMapping(value = Globals.EMPTY_PATH)
    public ResponseEntity<Long> addCalculation(@PathVariable Long projectId, @RequestBody @Valid Calculation calculation)
    {
        Project project = projectService.getProject(projectId);
        calculation.setId(null);
        calculation.setProject(project);
        calculationService.addCalculation(calculation);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path(Globals.CALCULATION_ID_PATH)
                .buildAndExpand(calculation.getId()).toUri();

        return ResponseEntity.created(location).body(calculation.getId());
    }

    @GetMapping(value = Globals.CALCULATION_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Calculation> getCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        Project project = projectService.getProject(projectId);
        Calculation calculation = calculationService.getCalculation(project, calculationId);

        return ResponseEntity.ok().body(calculation);
    }

    @PutMapping(value = Globals.CALCULATION_ID_PATH)
    public ResponseEntity<?> updateCalculation(@PathVariable Long projectId, @PathVariable Long calculationId, @RequestBody @Valid Calculation calculation)
    {
        Project project = projectService.getProject(projectId);
        calculation.setId(calculationId);
        calculation.setProject(project);
        calculationService.updateCalculation(calculation);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = Globals.CALCULATION_ID_PATH)
    public ResponseEntity<?> deleteCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        Project project = projectService.getProject(projectId);
        calculationService.deleteCalculation(project, calculationId);

        return ResponseEntity.ok().build();
    }
}
