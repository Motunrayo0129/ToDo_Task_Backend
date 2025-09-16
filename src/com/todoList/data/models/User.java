package com.todoList.data.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotBlank(message = "Full name required")
    private String fullName;

    @NotBlank(message = "Username required")
    private String userName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email required")
    private String email;


    @NotBlank(message = "Password required")
    private String password;

    @DBRef
    private List<ToDo> task = new ArrayList<>();
}
