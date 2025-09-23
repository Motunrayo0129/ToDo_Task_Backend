package com.todoList.dtos.responses;

import com.todoList.data.models.Priority;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class TaskResponse {

    private String taskId;
    private String title;
    private LocalDateTime dateAdded;
    private LocalDateTime dateCompleted;
    private boolean isCompleted;
    private Priority priority;

}
