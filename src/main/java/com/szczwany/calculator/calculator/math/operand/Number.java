package com.szczwany.calculator.calculator.math.operand;

import com.szczwany.calculator.calculator.math.IMathSign;

import java.math.BigDecimal;

public class Number implements IMathSign
{
    private BigDecimal value;

    public Number(BigDecimal value)
    {
        this.value = value;
    }

    public BigDecimal execute()
    {
        return value;
    }
}