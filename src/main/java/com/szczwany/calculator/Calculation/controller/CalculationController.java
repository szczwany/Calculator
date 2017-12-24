package com.szczwany.calculator.Calculation.controller;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Calculation.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/calculations")
public class CalculationController
{
    private CalculationService calculationService;

    @Autowired
    public CalculationController(CalculationService calculationService)
    {
        this.calculationService = calculationService;
    }

    @GetMapping(value = "")
    public List<Calculation> getCalculations()
    {
        return calculationService.getCalculations();
    }

    @GetMapping(value = "/{calculationId}")
    public Calculation getCalculation(@PathVariable Long calculationId)
    {
        return calculationService.getCalculation(calculationId);
    }

    @PostMapping(value = "")
    public Long addCalculation(@RequestBody @Valid Calculation calculation)
    {
        calculationService.addCalculation(calculation);

        return calculation.getId();
    }

    @PutMapping(value = "/{calculationId}")
    public void updateCalculation(@PathVariable Long calculationId, @RequestBody Calculation calculation)
    {
        calculationService.updateCalculation(calculationId, calculation);
    }

    @DeleteMapping(value = "/{calculationId}")
    public void updateCalculation(@PathVariable Long calculationId)
    {
        calculationService.deleteCalculation(calculationId);
    }

    @GetMapping(value = "/{calculationId}/calculate")
    public String calculate(@PathVariable Long calculationId)
    {
        return calculationService.calculate(calculationId);
    }
}
