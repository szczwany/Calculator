package com.szczwany.calculator.Calculation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CalculationNotFoundException extends RuntimeException
{
    public CalculationNotFoundException(Long calculationId)
    {
        super("Calculation id: " + calculationId + " could not be found!");
    }

    public CalculationNotFoundException(Long calculationId, Long projectId)
    {
        super("Calculation id: " + calculationId + " could not be found in project id: " + projectId);
    }
}
