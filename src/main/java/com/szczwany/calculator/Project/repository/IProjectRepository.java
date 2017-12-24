package com.szczwany.calculator.Project.repository;

import com.szczwany.calculator.Project.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends CrudRepository<Project, Long>
{
}
