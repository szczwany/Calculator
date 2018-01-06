package com.szczwany.calculator.Calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static com.szczwany.calculator.Calculator.Calculator.calculate;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class CalculatorTests
{
    @Test
    public void whenEmptyExpression_returnNull()
    {
        assertEquals(null, calculate(""));
    }

    @Test
    public void whenValidExpression_returnResult()
    {
        assertEquals(0, calculate("2-2*2/2"),0);
    }

    @Test
    public void whenInvalidExpression_returnNull()
    {
        assertEquals(null, calculate("2.22--1*3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenExpressionDivideByZero_returnNull()
    {
        calculate("2.22/0");
    }
}
