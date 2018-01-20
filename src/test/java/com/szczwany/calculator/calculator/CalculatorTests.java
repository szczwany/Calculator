package com.szczwany.calculator.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static com.szczwany.calculator.calculator.Calculator.calculate;
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
        assertEquals(BigDecimal.valueOf(0), calculate("2-2*2/2"));
    }

    @Test
    public void whenValidExpressionWithDot_returnResult()
    {
        assertEquals(BigDecimal.valueOf(-0.44), calculate("2-2.54*2.11/2.2"));
    }

    @Test
    public void whenInvalidExpression_returnNull()
    {
        assertEquals(null, calculate("2.22--1*3"));
    }

    @Test
    public void whenExpressionDivideByZero_returnNull()
    {
        assertEquals(null, calculate("2.22/0"));
    }
}
