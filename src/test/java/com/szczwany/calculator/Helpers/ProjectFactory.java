package com.szczwany.calculator.Helpers;

import com.szczwany.calculator.Project.model.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO refactor ... -> design pattern ?

public final class ProjectFactory
{
    public static List<Project> createProjects(int number)
    {
        Project project = createProject();

        List<Project> projects = new ArrayList<>();

        if( number > 1 )
        {
            for (int i = 0; i < number; i++)
            {
                projects.add(project);
            }
        }
        else
        {
            projects = Collections.singletonList(project);
        }

        return projects;
    }

    public static Project createProject()
    {
        Project project = new Project();
        project.setName("Test name");

        return project;
    }

    public static Project createProjectWithId()
    {
        Project project = new Project();
        project.setId(13L);
        project.setName("Test name");

        return project;
    }

    public static Project createEmptyProject()
    {
        return new Project();
    }
}
