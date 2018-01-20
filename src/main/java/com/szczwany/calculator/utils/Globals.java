package com.szczwany.calculator.utils;

public final class Globals
{
    // Regex
    public static final String MATH_EXPRESSION_REGEX = "((-?[0-9]+(\\.[0-9]{1,2})?)+[-+*/]{1}[0-9]+(\\.[0-9]{1,2})?)+([-+*/]{1}[0-9]+(\\.[0-9]{1,2})?)*";
    public static final String OPERATOR_REGEX = "[-+*/]";

    // Path
    public static final String PROJECTS_PATH = "/v1/projects";
    public static final String PROJECT_ID_PATH = "/{projectId}";
    public static final String CALCULATIONS_PATH = "/v1/projects/{projectId}/calculations";
    public static final String CALCULATION_ID_PATH = "/{calculationId}";
    public static final String ALL_CALCULATIONS_PATH = "/v1/calculations";
    public static final String EMPTY_PATH = "";
    public static final String RESULT_PATH = "/results";

    // Date
    public static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
    public static final int ONE_HOUR_MILISECONDS = 3600000;

    // Tests
    public static final int NUM_OF_PROJECTS_TEST = 10;
    public static final int NUM_OF_CALCULATIONS_TEST = 10;
    public static final Long TEST_ID = 77L;
    public static final String TEST_NAME = "Test name";
    public static final String TEST_DESCRIPTION = "Test description";
    public static final String TEST_EXPRESSION = "2+2";

    // Signs
   public static final String PLUS_SIGN = "+";
   public static final String MINUS_SIGN = "-";
   public static final String MULTIPLY_SIGN = "*";
   public static final String DIVIDE_SIGN = "/";

    private Globals()
    {
    }
}
