package com.todoList.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationRequest {

    @NotBlank(message = "Full name required")
    private String fullName;

    @NotBlank(message = "Username required")
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String id;

    @NotBlank(message = "Password required")
    private String password;
}
