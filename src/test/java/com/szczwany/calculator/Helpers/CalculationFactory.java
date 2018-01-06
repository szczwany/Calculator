package com.szczwany.calculator.Helpers;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Project.model.Project;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO refactor ... -> design pattern ?

public final class CalculationFactory
{
    private static final Long testId = 1L;
    private static final String testDescription = "Test description";
    private static final String testExpression = "2+2";

    public static List<Calculation> createCalculations(int elements)
    {
        Calculation calculation = createCalculation();
        List<Calculation> calculations = new ArrayList<>();

        if (elements > 1)
        {
            for (int i = 0; i < elements; i++)
            {
                calculations.add(calculation);
            }
        }
        else if(elements == 0)
        {
            return Lists.emptyList();
        }
        else
        {
            calculations = Collections.singletonList(calculation);
        }

        return calculations;
    }

    public static List<Calculation> createCalculations(Project project, int elements)
    {
        Calculation calculation = createCalculationWithProject(project);
        List<Calculation> calculations = new ArrayList<>();

        if (elements > 1)
        {
            for (int i = 0; i < elements; i++)
            {
                calculations.add(calculation);
            }
        } else
        {
            calculations = Collections.singletonList(calculation);
        }

        return calculations;
    }

    public static Calculation createEmptyCalculation()
    {
        return new Calculation();
    }

    public static Calculation createCalculation()
    {
        Calculation calculation = new Calculation();
        calculation.setDescription(testDescription);
        calculation.setExpression(testExpression);

        return calculation;
    }

    public static Calculation createCalculationWithProject(Project project)
    {
        Calculation calculation = new Calculation();
        calculation.setDescription(testDescription);
        calculation.setExpression(testExpression);
        calculation.setProject(project);

        return calculation;
    }

    public static Calculation createCalculationWithProjectAndId(Project project)
    {
        Calculation calculation = new Calculation();
        calculation.setId(testId);
        calculation.setDescription(testDescription);
        calculation.setExpression(testExpression);
        calculation.setProject(project);

        return calculation;
    }
}
