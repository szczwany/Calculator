package com.szczwany.calculator.Calculation.service;

import com.szczwany.calculator.Calculation.model.Calculation;

import java.util.List;

public interface ICalculationService
{
    List<Calculation> getCalculations(Long projectId);
    Calculation addCalculation(Long projectId, Calculation calculation);
    Calculation getCalculation(Long projectId, Long calculationId);
    void updateCalculation(Long projectId, Long calculationId, Calculation calculation);
    void deleteCalculation(Long projectId, Long calculationId);
}
