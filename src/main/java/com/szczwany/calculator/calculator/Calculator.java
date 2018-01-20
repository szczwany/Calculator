package com.szczwany.calculator.calculator;

import com.szczwany.calculator.calculator.math.IMathSign;
import com.szczwany.calculator.calculator.math.InfixToRPNConverter;
import com.szczwany.calculator.calculator.math.operand.Number;
import com.szczwany.calculator.calculator.math.operator.Divide;
import com.szczwany.calculator.calculator.math.operator.Minus;
import com.szczwany.calculator.calculator.math.operator.Multiply;
import com.szczwany.calculator.calculator.math.operator.Plus;

import java.math.BigDecimal;
import java.util.Stack;

import static com.szczwany.calculator.utils.Globals.*;

public final class Calculator
{
    private Calculator()
    {

    }

    public static BigDecimal calculate(String expression)
    {
        InfixToRPNConverter infixToRPNConverter = new InfixToRPNConverter();

        if (expression == null || expression.length() == 0 || !expression.matches(MATH_EXPRESSION_REGEX))
        {
            return null;
        }

        Stack<IMathSign> numbers = new Stack<>();
        Stack<String> elementsInExpression = infixToRPNConverter.infixToRPN(expression);
        IMathSign first, second;

        for(String element : elementsInExpression)
        {
            if (!isOperator(element))
            {
                numbers.push(new Number(new BigDecimal(element)));
            }
            else
            {
                second = numbers.pop();
                first = numbers.pop();

                if(second.execute().equals(BigDecimal.ZERO) && element.equals(DIVIDE_SIGN))
                {
                    return null;
                }

                numbers.push(getOperation(first, second, element));
            }
        }

        return numbers.pop().execute();
    }

    private static IMathSign getOperation(IMathSign first, IMathSign second, String element)
    {
        switch (element)
        {
            case PLUS_SIGN:
                return new Plus(first, second);
            case MINUS_SIGN:
                return new Minus(first, second);
            case MULTIPLY_SIGN:
                return new Multiply(first, second);
            case DIVIDE_SIGN:
                return new Divide(first, second);
            default:
                return null;
        }
    }

    public static boolean isOperator(String s)
    {
        return s.matches(OPERATOR_REGEX);
    }
}