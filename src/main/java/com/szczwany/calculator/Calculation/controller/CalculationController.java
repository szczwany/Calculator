package com.szczwany.calculator.Calculation.controller;

import com.szczwany.calculator.Calculation.exception.CalculationNotFoundException;
import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Calculation.service.CalculationService;
import com.szczwany.calculator.Project.exception.ProjectNotFoundException;
import com.szczwany.calculator.Project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

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

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Calculation>> getCalculations(@PathVariable Long projectId)
    {
        validateProject(projectId);

        return ResponseEntity.ok().body(calculationService.getCalculations(projectId));
    }

    @PostMapping(value = "")
    public ResponseEntity<Long> addCalculation(@PathVariable Long projectId, @RequestBody @Valid Calculation calculation)
    {
        validateProject(projectId);

        return Optional.ofNullable(calculationService.addCalculation(projectId, calculation))
                .map(c ->
                {
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{calculationId}")
                            .buildAndExpand(c.getId()).toUri();

                    return ResponseEntity.created(location).body(c.getId());
                })
                .orElseGet(() ->
                        ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/{calculationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Calculation> getCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        validateProject(projectId);

        return Optional.ofNullable(calculationService.getCalculation(projectId, calculationId))
                .map(c ->
                        ResponseEntity.ok().body(c))
                .orElseThrow(() ->
                        new CalculationNotFoundException(calculationId));
    }

    @PutMapping(value = "/{calculationId}")
    public ResponseEntity<?> updateCalculation(@PathVariable Long projectId, @PathVariable Long calculationId, @RequestBody @Valid Calculation calculation)
    {
        validateProject(projectId);

        return Optional.ofNullable(calculationService.getCalculation(projectId, calculationId))
                .map(c ->
                {
                    calculationService.updateCalculation(projectId, calculationId, calculation);

                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() ->
                        new CalculationNotFoundException(calculationId));
    }

    @DeleteMapping(value = "/{calculationId}")
    public ResponseEntity<?> updateCalculation(@PathVariable Long projectId, @PathVariable Long calculationId)
    {
        validateProject(projectId);

        return Optional.ofNullable(calculationService.getCalculation(projectId, calculationId))
                .map(c ->
                {
                    calculationService.deleteCalculation(projectId, calculationId);

                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() ->
                        new CalculationNotFoundException(calculationId));
    }

    private void validateProject(Long projectId)
    {
        if(projectService.getProject(projectId) == null)
        {
            throw new ProjectNotFoundException(projectId);
        }
    }
}
