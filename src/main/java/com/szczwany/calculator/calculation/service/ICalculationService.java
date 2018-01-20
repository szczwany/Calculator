package com.szczwany.calculator.calculation.service;

import com.szczwany.calculator.calculation.model.Calculation;
import com.szczwany.calculator.project.model.Project;

import java.util.List;

public interface ICalculationService
{
    List<Calculation> getCalculations();
    List<Calculation> getCalculationsByProject(Project project);
    void addCalculation(Calculation calculation);
    Calculation getCalculation(Project project, Long calculationId);
    void updateCalculation(Calculation calculation);
    void deleteCalculation(Long calculationId);
}
