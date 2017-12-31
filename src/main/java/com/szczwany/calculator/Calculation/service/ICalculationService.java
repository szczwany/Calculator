package com.szczwany.calculator.Calculation.service;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Project.model.Project;

import java.util.List;

public interface ICalculationService
{
    List<Calculation> getCalculations();
    List<Calculation> getCalculationsByProject(Project project);
    void addCalculation(Calculation calculation);
    Calculation getCalculation(Project project, Long calculationId);
    void updateCalculation(Calculation calculation);
    void deleteCalculation(Project project, Long calculationId);
}
