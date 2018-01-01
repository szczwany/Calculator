package com.szczwany.calculator.Project;

import com.szczwany.calculator.Helpers.ProjectFactory;
import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Project.repository.IProjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectRepositoryTests
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IProjectRepository projectRepository;

    // Not needed here just to test things
    @Test
    public void whenFindOne_thenReturnProject()
    {
        Project project = ProjectFactory.createProjectWithName("Tescik");
        entityManager.persist(project);
        entityManager.flush();

        Project foundProject = projectRepository.findOne(project.getId());

        assertThat(foundProject.getName().equals(project.getName()));
    }
}
