package com.szczwany.calculator.calculator.math.operator;

import com.szczwany.calculator.calculator.math.IMathSign;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Divide extends Operator
{
    public Divide(IMathSign left, IMathSign right)
    {
        super(left, right);
    }

    @Override
    public BigDecimal execute()
    {
        return left.execute().divide(right.execute(),2, RoundingMode.HALF_UP);
    }
}
