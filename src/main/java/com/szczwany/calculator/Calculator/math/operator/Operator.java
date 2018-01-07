package com.szczwany.calculator.Calculator.math.operator;

import com.szczwany.calculator.Calculator.math.IMathSign;

abstract class Operator implements IMathSign
{
    IMathSign left;
    IMathSign right;

    Operator(IMathSign left, IMathSign right)
    {
        this.left = left;
        this.right = right;
    }
}
