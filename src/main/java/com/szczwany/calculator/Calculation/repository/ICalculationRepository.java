package com.szczwany.calculator.Calculation.repository;

import com.szczwany.calculator.Calculation.model.Calculation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICalculationRepository extends CrudRepository<Calculation, Long>
{
}
