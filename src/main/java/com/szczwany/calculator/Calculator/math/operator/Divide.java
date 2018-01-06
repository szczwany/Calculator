package com.szczwany.calculator.Calculator.math.operator;

import com.szczwany.calculator.Calculator.math.IMathSign;

public class Divide extends AbstractOperator
{
    public Divide(IMathSign left, IMathSign right)
    {
        super(left, right);
    }

    // TODO proper error handling when dividing by 0
    @Override
    public Double execute()
    {
        return right.execute() != 0 ? left.execute() / right.execute() : null;
    }
}
