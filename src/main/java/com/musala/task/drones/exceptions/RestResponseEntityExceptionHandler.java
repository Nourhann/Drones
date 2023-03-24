package com.musala.task.drones.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ExceedWeightException.class , NotEnoughChargeException.class ,
            NotFoundException.class , ConstraintViolationException.class})
    public ResponseEntity<Object> handleExceptions(
            Exception ex) {
        return new ResponseEntity<> (
                ex.getMessage (), new HttpHeaders (), HttpStatus.BAD_REQUEST);
    }
}
