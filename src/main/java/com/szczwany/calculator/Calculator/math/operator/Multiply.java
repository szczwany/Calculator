package com.szczwany.calculator.Calculator.math.operator;

import com.szczwany.calculator.Calculator.math.IMathSign;

public class Multiply extends Operator
{
    public Multiply(IMathSign left, IMathSign right)
    {
        super(left, right);
    }

    @Override
    public Double execute()
    {
        return left.execute() * right.execute();
    }
}
