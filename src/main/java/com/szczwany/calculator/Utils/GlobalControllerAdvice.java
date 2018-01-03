package com.szczwany.calculator.Utils;

import com.szczwany.calculator.Calculation.exception.CalculationNotFoundException;
import com.szczwany.calculator.Project.exception.ProjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalControllerAdvice
{
    @ExceptionHandler({ProjectNotFoundException.class, CalculationNotFoundException.class, NoHandlerFoundException.class})
    ResponseEntity<ApiError> handleNotFound(Exception e)
    {
        ApiError apiError = new ApiError();
        apiError.initializeErrorData(HttpStatus.NOT_FOUND, e);

        return new ResponseEntity<>(apiError, apiError.getErrorStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> invalidInput(MethodArgumentNotValidException e)
    {
        ApiError apiError = new ApiError();
        apiError.initializeErrorData(HttpStatus.BAD_REQUEST, e);

        return new ResponseEntity<>(apiError, apiError.getErrorStatus());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiError> methodNotSupported(HttpRequestMethodNotSupportedException e)
    {
        ApiError apiError = new ApiError();
        apiError.initializeErrorData(HttpStatus.METHOD_NOT_ALLOWED, e);

        return new ResponseEntity<>(apiError, apiError.getErrorStatus());
    }
}
