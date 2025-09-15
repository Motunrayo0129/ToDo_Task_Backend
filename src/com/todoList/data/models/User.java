package com.todoList.data.models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document("Registration")
public class Registration {

    @NotBlank(message = "Full name required")
    private String fullName;

    @NotBlank(message = "Full name required")
    private String userName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Full name required")
    private String email;

    @NotBlank(message = "Full name required")
    private String password;


}
