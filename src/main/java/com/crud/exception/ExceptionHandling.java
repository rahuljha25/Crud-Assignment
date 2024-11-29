package com.crud.exception;

import com.crud.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> ProductNotFoundException(
            ProductNotFoundException e,
            WebRequest webRequest
    ){
        ErrorDetails error=new ErrorDetails(
                e.getMessage(),
                new Date(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalException(
            Exception e,
            WebRequest webRequest
    ){
        ErrorDetails error=new ErrorDetails(
                e.getMessage(),
                new Date(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
