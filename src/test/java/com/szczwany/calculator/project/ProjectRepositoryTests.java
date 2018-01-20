package com.szczwany.calculator.project;

import com.szczwany.calculator.helpers.ProjectFactory;
import com.szczwany.calculator.project.model.Project;
import com.szczwany.calculator.project.repository.IProjectRepository;
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
public class ProjectRepositoryTests
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IProjectRepository projectRepository;

    // Test
    @Test
    public void whenFindOne_thenReturnProject()
    {
        Project project = ProjectFactory.createProject();
        entityManager.persist(project);
        entityManager.flush();

        Project foundProject = projectRepository.findOne(project.getId());

        assertThat(foundProject.getName().equals(project.getName()));
    }

    @Test
    public void whenFindOneNotExist_thenReturnNull()
    {
        assertNull(projectRepository.findOne(TEST_ID));
    }
}
