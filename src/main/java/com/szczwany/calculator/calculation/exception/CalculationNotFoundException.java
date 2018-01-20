package com.szczwany.calculator.calculation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CalculationNotFoundException extends RuntimeException
{
    public CalculationNotFoundException(Long calculationId)
    {
        super("calculation '" + calculationId + "' does not exist");
    }
}
