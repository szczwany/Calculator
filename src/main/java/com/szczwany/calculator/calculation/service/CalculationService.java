package com.szczwany.calculator.calculation.service;

import com.szczwany.calculator.calculation.exception.CalculationNotFoundException;
import com.szczwany.calculator.calculation.model.Calculation;
import com.szczwany.calculator.calculation.repository.ICalculationRepository;
import com.szczwany.calculator.project.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalculationService implements ICalculationService
{
    private ICalculationRepository calculationRepository;

    @Autowired
    public CalculationService(ICalculationRepository calculationRepository)
    {
        this.calculationRepository = calculationRepository;
    }

    @Override
    public List<Calculation> getCalculations()
    {
        List<Calculation> calculations = new ArrayList<>();
        calculationRepository.findAll().forEach(calculations::add);

        return calculations;
    }

    @Override
    public List<Calculation> getCalculationsByProject(Project project)
    {
        return calculationRepository.findByProject(project);
    }

    @Override
    public void addCalculation(Calculation calculation)
    {
        calculationRepository.save(calculation);
    }

    @Override
    public Calculation getCalculation(Project project, Long calculationId)
    {
        return Optional.ofNullable(calculationRepository.findByProjectAndId(project, calculationId))
                .orElseThrow(() ->
                        new CalculationNotFoundException(calculationId));
    }

    @Override
    public void updateCalculation(Calculation calculation)
    {
        calculationRepository.save(calculation);
    }

    @Override
    public void deleteCalculation(Long calculationId)
    {
        calculationRepository.delete(calculationId);
    }
}
