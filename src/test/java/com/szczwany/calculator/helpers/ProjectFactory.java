package com.szczwany.calculator.helpers;

import com.szczwany.calculator.project.model.Project;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.szczwany.calculator.utils.Globals.*;

// TODO refactor ... -> design pattern ?

public final class ProjectFactory
{
    public static List<Project> createProjects(int elements)
    {
        Project project = createProject();

        List<Project> projects = new ArrayList<>();

        if( elements > 1 )
        {
            for (int i = 0; i < elements; i++)
            {
                projects.add(project);
            }
        }
        else if(elements == 0)
        {
            return Lists.emptyList();
        }
        else
        {
            projects = Collections.singletonList(project);
        }

        return projects;
    }

    public static Project createEmptyProject()
    {
        return new Project();
    }

    public static Project createProject()
    {
        Project project = new Project();
        project.setName(TEST_NAME);

        return project;
    }

    public static Project createProjectWithId()
    {
        Project project = new Project();
        project.setId(TEST_ID);
        project.setName(TEST_NAME);

        return project;
    }
}
