package com.szczwany.calculator.Calculation.exception;

public class CalculationNotFoundException extends RuntimeException
{
    public CalculationNotFoundException(Long calculationId)
    {
        super("Calculation with id :" + calculationId + " could not be found!");
    }
}
