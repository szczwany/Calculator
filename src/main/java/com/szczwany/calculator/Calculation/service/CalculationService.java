package com.szczwany.calculator.Calculation.service;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Calculation.repository.ICalculationRepository;
import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationService implements ICalculationService
{
    private IProjectRepository iProjectRepository;
    private ICalculationRepository iCalculationRepository;

    @Autowired
    public CalculationService(IProjectRepository iProjectRepository, ICalculationRepository iCalculationRepository)
    {
        this.iProjectRepository = iProjectRepository;
        this.iCalculationRepository = iCalculationRepository;
    }

    @Override
    public List<Calculation> getCalculations(Long projectId)
    {
        return iCalculationRepository.findByProjectId(projectId);
    }

    @Override
    public Calculation addCalculation(Long projectId, Calculation calculation)
    {
        Project project = iProjectRepository.findOne(projectId);

        calculation.setProject(project);

        return iCalculationRepository.save(calculation);
    }

    @Override
    public Calculation getCalculation(Long projectId, Long calculationId)
    {
        return iCalculationRepository.findByProjectIdAndId(projectId, calculationId);
    }

    @Override
    public void updateCalculation(Long projectId, Long calculationId, Calculation calculation)
    {
        Project project = iProjectRepository.findOne(projectId);

        calculation.setId(calculationId);
        calculation.setProject(project);

        iCalculationRepository.save(calculation);
    }

    @Override
    public void deleteCalculation(Long projectId, Long calculationId)
    {
        iCalculationRepository.delete(calculationId);
    }
}
