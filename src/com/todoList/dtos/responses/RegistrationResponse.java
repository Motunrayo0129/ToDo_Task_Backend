package com.todoList.dtos.responses;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class RegistrationResponse {

    private String userId;
   private String message;

}
