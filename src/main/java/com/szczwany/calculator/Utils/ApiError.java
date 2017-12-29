package com.szczwany.calculator.Utils;

import org.springframework.http.HttpStatus;

public class ApiError
{
    private String errorCode;
    private HttpStatus errorStatus;
    private String errorMessage;
    private String error;

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public HttpStatus getErrorStatus()
    {
        return errorStatus;
    }

    public void setErrorStatus(HttpStatus errorStatus)
    {
        this.errorStatus = errorStatus;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }
}
