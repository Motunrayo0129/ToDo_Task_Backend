package com.todoList.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidLoginResponse.class)
    public ResponseEntity<?> handleInvalidLoginResponse(InvalidLoginResponse ex){
        return ResponseEntity.status(400).body(Map.of(
                "message", ex.getMessage()));

    }
    @ExceptionHandler(InvalidRegistrationException.class)
   public ResponseEntity<?> handleInvalidRegistrationException(InvalidRegistrationException ex){
        return ResponseEntity.status(400).body(Map.of(
                "message", ex.getMessage(), "status", 400));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return ResponseEntity.status(400).body(Map.of(
                "message", errorMessage
        ));
    }



    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<?> handleTaskNotFoundException(TaskNotFoundException ex){
        return ResponseEntity.status(404).body(Map.of(
                "message", ex.getMessage()));
    }
}
