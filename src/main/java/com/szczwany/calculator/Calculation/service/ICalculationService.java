package com.szczwany.calculator.Calculation.service;

import com.szczwany.calculator.Calculation.model.Calculation;

import java.util.List;

public interface ICalculationService
{
    List<Calculation> getCalculations();
    void addCalculation(Calculation calculation);
    Calculation getCalculation(Long calculationId);
    void updateCalculation(Long calculationId, Calculation calculation);
    void deleteCalculation(Long calculationId);

    String calculate(Long calculationId);
}
