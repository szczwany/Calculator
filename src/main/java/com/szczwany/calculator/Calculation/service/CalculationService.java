package com.szczwany.calculator.Calculation.service;

import com.szczwany.calculator.Calculation.exception.CalculationNotFoundException;
import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Calculation.repository.ICalculationRepository;
import com.szczwany.calculator.Project.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class CalculationService implements ICalculationService
{
    private ICalculationRepository iCalculationRepository;

    @Autowired
    public CalculationService(ICalculationRepository iCalculationRepository)
    {
        this.iCalculationRepository = iCalculationRepository;
    }

    @Override
    public List<Calculation> getCalculations(Project project)
    {
        return iCalculationRepository.findByProject(project);
    }

    @Override
    public void addCalculation(Project project, Calculation calculation)
    {
        calculation.setProject(project);

        iCalculationRepository.save(calculation);
    }

    @Override
    public Calculation getCalculation(Project project, Long calculationId)
    {
        return validateCalculation(project, calculationId);
    }

    @Override
    public void updateCalculation(Project project, Long calculationId, Calculation calculation)
    {
        validateCalculation(project, calculationId);

        calculation.setId(calculationId);
        calculation.setProject(project);

        iCalculationRepository.save(calculation);
    }

    @Override
    public void deleteCalculation(Project project, Long calculationId)
    {
        validateCalculation(project, calculationId);

        iCalculationRepository.delete(calculationId);
    }

    @Override
    public Calculation validateCalculation(Project project, Long calculationId)
    {
        Calculation tempCalculation = iCalculationRepository.findOne(calculationId);

        if (tempCalculation != null)
        {
            if(tempCalculation.getProject().getId().equals(project.getId()))
            {
                return tempCalculation;
            }
            else
            {
                throw new CalculationNotFoundException(calculationId, project.getId());
            }
        }
        else
        {
            throw new CalculationNotFoundException(calculationId);
        }
    }

    // todo expression value is Integer -> String, needs to be Double??
    @Override
    public String calculate(Long calculationId)
    {
        Calculation calculation = iCalculationRepository.findOne(calculationId);

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(calculation.getExpression());
        String result = exp.getValue().toString();

        calculation.setResult(result);
        calculation.setLastUpdate(Date.from(Instant.now()));

        iCalculationRepository.save(calculation);

        return calculation.getResult();
    }
}
