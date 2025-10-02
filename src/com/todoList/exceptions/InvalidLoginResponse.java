package com.todoList.exceptions;


public class InvalidLoginResponse extends RuntimeException {
    public InvalidLoginResponse(String message) {
        super(message);
    }
}
