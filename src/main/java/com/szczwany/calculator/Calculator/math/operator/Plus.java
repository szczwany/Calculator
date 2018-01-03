package com.szczwany.calculator.Calculator.math.operator;

import com.szczwany.calculator.Calculator.math.IMathSign;

public class Plus implements IMathSign
{
    private IMathSign left;
    private IMathSign right;

    public Plus(IMathSign left, IMathSign right)
    {
        this.left = left;
        this.right = right;
    }

    @Override
    public Double execute()
    {
        return left.execute() + right.execute();
    }
}
