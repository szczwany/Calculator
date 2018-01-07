package com.szczwany.calculator.Calculator.math.operator;

import com.szczwany.calculator.Calculator.math.IMathSign;

abstract class AbstractOperator implements IMathSign
{
    IMathSign left;
    IMathSign right;

    AbstractOperator(IMathSign left, IMathSign right)
    {
        this.left = left;
        this.right = right;
    }
}
