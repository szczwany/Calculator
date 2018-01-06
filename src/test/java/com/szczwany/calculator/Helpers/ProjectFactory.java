package com.szczwany.calculator.Helpers;

import com.szczwany.calculator.Project.model.Project;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO refactor ... -> design pattern ?

public final class ProjectFactory
{
    private static final Long testId = 13L;
    private static final String testName = "Test name";

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
        project.setName(testName);

        return project;
    }

    public static Project createProjectWithId()
    {
        Project project = new Project();
        project.setId(testId);
        project.setName(testName);

        return project;
    }
}
