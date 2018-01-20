package com.szczwany.calculator.helpers;

import com.szczwany.calculator.calculation.model.Calculation;
import com.szczwany.calculator.project.model.Project;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.szczwany.calculator.utils.Globals.*;

// TODO refactor ... -> design pattern ?

public final class CalculationFactory
{
    private CalculationFactory()
    {

    }

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
        calculation.setDescription(TEST_DESCRIPTION);
        calculation.setExpression(TEST_EXPRESSION);

        return calculation;
    }

    public static Calculation createCalculationWithProject(Project project)
    {
        Calculation calculation = new Calculation();
        calculation.setDescription(TEST_DESCRIPTION);
        calculation.setExpression(TEST_EXPRESSION);
        calculation.setProject(project);

        return calculation;
    }

    public static Calculation createCalculationWithProjectAndId(Project project)
    {
        Calculation calculation = new Calculation();
        calculation.setId(TEST_ID);
        calculation.setDescription(TEST_DESCRIPTION);
        calculation.setExpression(TEST_EXPRESSION);
        calculation.setProject(project);

        return calculation;
    }
}
