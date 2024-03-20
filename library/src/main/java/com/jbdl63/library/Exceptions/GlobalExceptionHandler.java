package com.jbdl63.library.Exceptions;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlerValidationExceptions(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest){
        Map<String, String> errorInfo = new LinkedHashMap<>();
        e.getBindingResult().getAllErrors().forEach( error-> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();

            errorInfo.put(fieldName, errorMessage);
        });
        return  new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handlerBadRequestExceptions(BadRequestException e, HttpServletRequest httpServletRequest){

        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handlerDataNotFoundExceptions(BadRequestException e, HttpServletRequest httpServletRequest){

        return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
