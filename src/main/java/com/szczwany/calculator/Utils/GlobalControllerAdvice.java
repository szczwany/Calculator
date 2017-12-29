package com.szczwany.calculator.Utils;

import com.szczwany.calculator.Calculation.exception.CalculationNotFoundException;
import com.szczwany.calculator.Project.exception.ProjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice
{
    @ExceptionHandler({ProjectNotFoundException.class, CalculationNotFoundException.class})
    ResponseEntity<ApiError> handleNotFound(Exception e)
    {
        ApiError apiError = new ApiError();
        apiError.setErrorCode(HttpStatus.NOT_FOUND.toString());
        apiError.setErrorMessage(e.getMessage());
        apiError.setErrorStatus(HttpStatus.NOT_FOUND);
        apiError.setError(e.getClass().getSimpleName());

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> invalidInput(MethodArgumentNotValidException e)
    {
        ApiError apiError = new ApiError();
        apiError.setErrorCode(HttpStatus.BAD_REQUEST.toString());
        apiError.setErrorMessage(e.getMessage());
        apiError.setErrorStatus(HttpStatus.BAD_REQUEST);
        apiError.setError(e.getClass().getSimpleName());

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
