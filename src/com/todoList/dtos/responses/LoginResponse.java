package com.todoList.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
public class LoginResponse {

    private String userId;
    private String username;
    private String email;
    private String message;
}
