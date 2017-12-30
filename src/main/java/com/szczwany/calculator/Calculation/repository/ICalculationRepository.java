package com.szczwany.calculator.Calculation.repository;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Project.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICalculationRepository extends CrudRepository<Calculation, Long>
{
    List<Calculation> findByProject(Project project);
    Calculation findByProjectAndId(Project project, Long calculationId);
}
