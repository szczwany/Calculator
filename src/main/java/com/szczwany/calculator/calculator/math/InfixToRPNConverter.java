package com.szczwany.calculator.calculator.math;

import com.szczwany.calculator.calculator.Calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static com.szczwany.calculator.utils.Globals.*;

public class InfixToRPNConverter
{
    private Map<String, Integer> precedence;
    private Stack<String> queue;
    private Stack<String> stack;
    private StringBuilder number;

    public InfixToRPNConverter()
    {
        initConverter();
        initPrecedence();
    }

    private void initConverter()
    {
        precedence = new HashMap<>();
        queue = new Stack<>();
        stack = new Stack<>();
        number = new StringBuilder();
    }

    private void initPrecedence()
    {
        precedence.put(PLUS_SIGN, 0);
        precedence.put(MINUS_SIGN, 0);
        precedence.put(MULTIPLY_SIGN, 1);
        precedence.put(DIVIDE_SIGN, 1);
    }

    ///
    // Reverse Polish Notation algorithm :D + decimal numbers
    ///
    public Stack<String> infixToRPN(String expression)
    {
        String[] elementsInExpression = expression.split("");

        boolean negative = false;

        if (elementsInExpression[0].equals(MINUS_SIGN))
        {
            number.append('-');
            negative = true;
        }

        for (String element : elementsInExpression)
        {
            if (negative)
            {
                negative = false;

                continue;
            }

            if (Calculator.isOperator(element))
            {
                saveNumber();

                while (!stack.empty() &&
                        (precedence.get(stack.peek()) >= precedence.get(element)))
                {
                    queue.push(stack.pop());
                }

                stack.push(element);
            } else
            {
                number.append(element);
            }
        }

        if (number.length() > 0)
        {
            saveNumber();
        }

        while (!stack.empty())
        {
            queue.push(stack.pop());
        }

        return queue;
    }

    private void saveNumber()
    {
        queue.push(number.toString());
        number.setLength(0);
    }
}
