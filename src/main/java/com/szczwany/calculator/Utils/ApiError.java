package com.szczwany.calculator.Utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ApiError
{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timeStamp;
    private String errorCode;
    private HttpStatus errorStatus;
    private String errorMessage;
    private String error;

    public ApiError()
    {
        this.timeStamp = new Date();
    }

    public void initializeErrorData(HttpStatus status, Exception exception)
    {
        this.setErrorCode(status.toString());
        this.setErrorMessage(exception.getMessage());
        this.setErrorStatus(status);
        this.setError(exception.getClass().getSimpleName());
    }

    public Date getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp)
    {
        this.timeStamp = timeStamp;
    }

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
