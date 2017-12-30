package com.szczwany.calculator.Calculation.service;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Project.model.Project;

import java.util.List;

public interface ICalculationService
{
    List<Calculation> getCalculations(Project project);
    Calculation addCalculation(Project project, Calculation calculation);
    Calculation getCalculation(Project project, Long calculationId);
    void updateCalculation(Project project, Long calculationId, Calculation calculation);
    void deleteCalculation(Project project, Long calculationId);
}
