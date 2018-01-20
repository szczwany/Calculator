package com.szczwany.calculator.calculation;

import com.szczwany.calculator.calculation.model.Calculation;
import com.szczwany.calculator.calculation.repository.ICalculationRepository;
import com.szczwany.calculator.helpers.CalculationFactory;
import com.szczwany.calculator.helpers.ProjectFactory;
import com.szczwany.calculator.project.model.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static com.szczwany.calculator.utils.Globals.TEST_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

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

    @Test
    public void whenFindByProjectAndIdNotExist_thenReturnNull()
    {
        Project project = ProjectFactory.createProject();
        entityManager.persist(project);
        entityManager.flush();

        assertNull(calculationRepository.findByProjectAndId(project, TEST_ID));
    }
}
