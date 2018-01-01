package com.szczwany.calculator.Helpers;

import com.szczwany.calculator.Project.model.Project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class ProjectFactory
{
    public static List<Project> createProjects(int number)
    {
        Project project = new Project();
        project.setName("Test name");
        project.setId(13L);

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
        project.setId(13L);

        return project;
    }

    public static Project createProjectWithName(String name)
    {
        Project project = new Project();
        project.setName(name);

        return project;
    }

    public static Project createEmptyProject()
    {
        return new Project();
    }
}
