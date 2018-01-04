package com.szczwany.calculator.Calculator.math.operator;

import com.szczwany.calculator.Calculator.math.IMathSign;

public class Divide implements IMathSign
{
    private IMathSign left;
    private IMathSign right;

    public Divide(IMathSign left, IMathSign right)
    {
        this.left = left;
        this.right = right;
    }

    // TODO proper error handling when dividing by 0
    @Override
    public Double execute()
    {
        return right.execute() != 0 ? left.execute() / right.execute() : null;
    }
}
