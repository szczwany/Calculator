package com.szczwany.calculator.Helpers;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Project.model.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO refactor ... -> design pattern ?

public final class CalculationFactory
{
    public static List<Calculation> createCalculations(int number)
    {
        Calculation calculation = createCalculation();

        List<Calculation> calculations = new ArrayList<>();

        if (number > 1)
        {
            for (int i = 0; i < number; i++)
            {
                calculations.add(calculation);
            }
        }
        else
        {
            calculations = Collections.singletonList(calculation);
        }

        return calculations;
    }

    public static List<Calculation> createCalculations(Project project, int number)
    {
        Calculation calculation = createCalculationWithProject(project);

        List<Calculation> calculations = new ArrayList<>();

        if (number > 1)
        {
            for (int i = 0; i < number; i++)
            {
                calculations.add(calculation);
            }
        } else
        {
            calculations = Collections.singletonList(calculation);
        }

        return calculations;
    }

    public static Calculation createCalculation()
    {
        Calculation calculation = new Calculation();
        calculation.setDescription("Test description");
        calculation.setExpression("2+2");

        return calculation;
    }

    public static Calculation createCalculationWithProject(Project project)
    {
        Calculation calculation = new Calculation();
        calculation.setDescription("Test description");
        calculation.setExpression("2+2");
        calculation.setProject(project);

        return calculation;
    }

    public static Calculation createCalculationWithProjectAndId(Project project)
    {
        Calculation calculation = new Calculation();
        calculation.setId(1L);
        calculation.setDescription("Test description");
        calculation.setExpression("2+2");
        calculation.setProject(project);

        return calculation;
    }

    public static Calculation createEmptyCalculation()
    {
        return new Calculation();
    }
}
