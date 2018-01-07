package com.szczwany.calculator.Calculation.model;

import com.szczwany.calculator.Calculation.service.CalculationService;
import com.szczwany.calculator.Calculator.Calculator;

public class ResultThread implements Runnable
{
    private Calculation calculation;
    private CalculationService calculationService;

    public ResultThread(Calculation calculation, CalculationService calculationService)
    {
        this.calculation = calculation;
        this.calculationService = calculationService;
    }

    @Override
    public void run()
    {
        getAndSaveCalculationResult();
    }

    private void getAndSaveCalculationResult()
    {
        Double result = Calculator.calculate(calculation.getExpression());

        if(result != null)
        {
            calculation.setResultAndUpdatedAt(result);
            calculationService.updateCalculation(calculation);
        }
    }
}
