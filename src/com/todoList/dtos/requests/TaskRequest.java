package com.todoList.dtos.requests;

import com.todoList.data.models.Priority;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskRequest {

    @NotNull(message = "Task title is required")
    private String title;
    @NotNull(message = "Task priority is required")
    private Priority priority;

}
