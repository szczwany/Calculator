package com.szczwany.calculator.Calculator.math.operand;

import com.szczwany.calculator.Calculator.math.IMathSign;

public class Number implements IMathSign
{
    private Double value;

    public Number(Double value)
    {
        this.value = value;
    }

    public Double execute()
    {
        return value;
    }
}