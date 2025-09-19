package com.todoList.dtos.responses;

import com.todoList.data.models.Priority;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {

    private String taskId;
    private String title;
    private LocalDateTime dateAdded;
    private LocalDateTime dateCompleted;
    private boolean isCompleted;
    private Priority priority;

}
