package com.szczwany.calculator.Utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Date;

import static com.szczwany.calculator.Utils.Globals.DATE_TIME_FORMAT;
import static com.szczwany.calculator.Utils.Globals.ONE_HOUR_MILISECONDS;

public class ApiError
{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    private Date timeStamp;
    private String errorCode;
    private HttpStatus errorStatus;
    private String errorMessage;
    private String error;

    public ApiError()
    {
        this.timeStamp = new Timestamp(System.currentTimeMillis() + ONE_HOUR_MILISECONDS);
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

    private void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public HttpStatus getErrorStatus()
    {
        return errorStatus;
    }

    private void setErrorStatus(HttpStatus errorStatus)
    {
        this.errorStatus = errorStatus;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getError()
    {
        return error;
    }

    private void setError(String error)
    {
        this.error = error;
    }
}
