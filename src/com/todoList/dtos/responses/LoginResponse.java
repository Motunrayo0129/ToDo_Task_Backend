package com.todoList.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
public class LoginResponse {

    @Id
    private String userId;
    private String message;

}
