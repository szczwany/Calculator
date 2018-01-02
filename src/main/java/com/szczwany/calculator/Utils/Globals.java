package com.szczwany.calculator.Utils;

public final class Globals
{
    public static final String MATH_EXPRESSION_REGEX = "((-?[0-9]+(\\.[0-9]{1,2})?)+[-+*/]{1}[0-9]+(\\.[0-9]{1,2})?)+([-+*/]{1}[0-9]+(\\.[0-9]{1,2})?)*";

    public static final String PROJECTS_PATH = "/v1/projects";
    public static final String PROJECT_ID = "/{projectId}";
    public static final String CALCULATIONS_PATH = "/v1/projects/{projectId}/calculations";
    public static final String CALCULATION_ID = "/{calculationId}";
    public static final String ALL_CALCULATIONS_PATH = "/v1/calculations";
    public static final String EMPTY_PATH = "";

    private Globals()
    {
    }
}
