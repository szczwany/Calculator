package com.szczwany.calculator.calculation.repository;

import com.szczwany.calculator.calculation.model.Calculation;
import com.szczwany.calculator.project.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICalculationRepository extends CrudRepository<Calculation, Long>
{
    List<Calculation> findByProject(Project project);
    Calculation findByProjectAndId(Project project, Long calculationId);
}
