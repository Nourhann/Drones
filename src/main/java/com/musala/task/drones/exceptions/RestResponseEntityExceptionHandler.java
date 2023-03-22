package com.musala.task.drones.exceptions;

import com.musala.task.drones.exceptions.ExceedWeightException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ExceedWeightException.class , NotEnoughChargeException.class , NotFoundException.class })
    public ResponseEntity<Object> handleExceedWeightException(
            Exception ex) {
        return new ResponseEntity<> (
                ex.getMessage (), new HttpHeaders (), HttpStatus.BAD_REQUEST);
    }
}
