package com.szczwany.calculator.Calculator;

import com.szczwany.calculator.Calculator.math.*;
import com.szczwany.calculator.Calculator.math.operand.Number;
import com.szczwany.calculator.Calculator.math.operator.Divide;
import com.szczwany.calculator.Calculator.math.operator.Minus;
import com.szczwany.calculator.Calculator.math.operator.Multiply;
import com.szczwany.calculator.Calculator.math.operator.Plus;

import java.util.Stack;

public class Calculator
{
    public static Double calculate(String expression)
    {
        InfixToRPNConverter infixToRPNConverter = new InfixToRPNConverter();

        if (expression == null || expression.length() == 0)
        {
            return null;
        }

        Stack<String> expressionRPN = infixToRPNConverter.infixToRPN(expression);
        
        Stack<IMathSign> numbers = new Stack<>();

        for(String s : expressionRPN)
        {
            if (!isOperator(s))
            {
                numbers.push(new Number(Double.valueOf(s)));
            }
            else
            {
                IMathSign second = numbers.pop();
                IMathSign first = numbers.pop();

                switch (s)
                {
                    case "+":
                        numbers.push(new Plus(first, second));
                        break;
                    case "-":
                        numbers.push(new Minus(first, second));
                        break;
                    case "*":
                        numbers.push(new Multiply(first, second));
                        break;
                    case "/":
                        numbers.push(new Divide(first, second));
                        break;
                }
            }
        }

        return numbers.pop().execute();
    }

    public static boolean isOperator(String s)
    {
        return s.matches("[-+*/]");
    }
}