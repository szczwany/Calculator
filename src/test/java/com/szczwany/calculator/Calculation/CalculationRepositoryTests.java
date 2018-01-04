package com.szczwany.calculator.Calculation;

import com.szczwany.calculator.Calculation.model.Calculation;
import com.szczwany.calculator.Calculation.repository.ICalculationRepository;
import com.szczwany.calculator.Helpers.CalculationFactory;
import com.szczwany.calculator.Helpers.ProjectFactory;
import com.szczwany.calculator.Project.model.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CalculationRepositoryTests
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ICalculationRepository calculationRepository;

    @Test
    public void whenFindByProjectAndId_thenReturnCalculation()
    {
        Project project = ProjectFactory.createProject();
        Calculation calculation = CalculationFactory.createCalculationWithProject(project);

        entityManager.persist(project);
        entityManager.persist(calculation);
        entityManager.flush();

        Calculation foundCalculation = calculationRepository.findByProjectAndId(project, calculation.getId());

        assertThat(foundCalculation.getDescription()).isEqualTo(calculation.getDescription());
        assertThat(foundCalculation.getExpression()).isEqualTo(calculation.getExpression());
        assertThat(foundCalculation.getProject().equals(calculation.getProject()));
    }
}