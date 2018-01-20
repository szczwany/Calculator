package com.szczwany.calculator.calculator.math.operator;

import com.szczwany.calculator.calculator.math.IMathSign;

import java.math.BigDecimal;

public class Minus extends Operator
{
    public Minus(IMathSign left, IMathSign right)
    {
        super(left, right);
    }

    @Override
    public BigDecimal execute()
    {
        return left.execute().subtract(right.execute());
    }
}
