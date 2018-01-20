package com.szczwany.calculator.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException
{
    public ProjectNotFoundException(Long projectId)
    {
        super("project '" + projectId + "' does not exist");
    }
}
