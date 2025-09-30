package com.ecommerce.sb_ecom_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<ErrorResponse> myResourceNotFound(ResourceNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public  ResponseEntity<ErrorResponse> myAPIException(APIException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
