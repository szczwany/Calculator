package com.szczwany.calculator.Calculation.service;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Calculation.repository.ICalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Calculation> getCalculations()
    {
        List<Calculation> calculations = new ArrayList<>();

        iCalculationRepository.findAll().forEach(calculations::add);

        return calculations;
    }

    @Override
    public void addCalculation(Calculation calculation)
    {
        iCalculationRepository.save(calculation);
    }

    @Override
    public Calculation getCalculation(Long calculationId)
    {
        return iCalculationRepository.findOne(calculationId);
    }

    @Override
    public void updateCalculation(Long calculationId, Calculation calculation)
    {
        calculation.setId(calculationId);
        iCalculationRepository.save(calculation);
    }

    @Override
    public void deleteCalculation(Long calculationId)
    {
        iCalculationRepository.delete(calculationId);
    }

    // todo expression value is Integer -> String, needs to be Double??
    @Override
    public String calculate(Long calculationId)
    {
        Calculation calculation = getCalculation(calculationId);

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(calculation.getExpression());
        String result = exp.getValue().toString();

        calculation.setResult(result);

        this.updateCalculation(calculationId, calculation);

        return calculation.getResult();
    }
}
