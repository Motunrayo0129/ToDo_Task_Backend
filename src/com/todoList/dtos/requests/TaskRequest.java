package com.todoList.dtos.requests;

import com.todoList.data.models.Priority;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class TaskRequest {


    private String userId;
    @NotNull(message = "Task title is required")
    private String title;
    @NotNull(message = "Task priority is required")
    private Priority priority;

}
