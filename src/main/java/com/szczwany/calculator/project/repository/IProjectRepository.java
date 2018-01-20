package com.szczwany.calculator.project.repository;

import com.szczwany.calculator.project.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends CrudRepository<Project, Long>
{
}
