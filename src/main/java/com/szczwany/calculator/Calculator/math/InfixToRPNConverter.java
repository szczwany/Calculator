package com.szczwany.calculator.Calculator.math;

import com.szczwany.calculator.Calculator.Calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InfixToRPNConverter
{
    private Map<String, Integer> precedence;
    private Stack<String> queue;
    private Stack<String> stack;

    private StringBuilder number;
    public InfixToRPNConverter()
    {
        this.precedence = new HashMap<>();
        precedence.put("+", 0);
        precedence.put("-", 0);
        precedence.put("*", 1);
        precedence.put("/", 1);

        queue = new Stack<>();
        stack = new Stack<>();

        number = new StringBuilder();
    }

    ///
    // Reverse Polish Notation algorithm :D + decimal numbers
    ///
    public Stack<String> infixToRPN(String expression)
    {
        String[] expressionElements = expression.split("");

        boolean negative = false;

        if (expressionElements[0].equals("-"))
        {
            number.append('-');
            negative = true;
        }

        for (String s : expressionElements)
        {
            if (negative)
            {
                negative = false;

                continue;
            }

            if (Calculator.isOperator(s))
            {
                saveNumber(queue, number);

                while (!stack.empty() &&
                        (precedence.get(stack.peek()) >= precedence.get(s)))
                {
                    queue.push(stack.pop());
                }

                stack.push(s);
            } else
            {
                number.append(s);
            }
        }

        if (number.length() > 0)
        {
            saveNumber(queue, number);
        }

        while (!stack.empty())
        {
            queue.push(stack.pop());
        }

        return queue;
    }

    private void saveNumber(Stack<String> queue, StringBuilder number)
    {
        queue.push(number.toString());
        number.setLength(0);
    }
}
