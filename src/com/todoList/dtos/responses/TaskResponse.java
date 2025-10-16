package com.todoList.dtos.responses;

import com.todoList.data.models.Priority;
import lombok.Data;

import java.time.LocalDate;


@Data
public class TaskResponse {

    private String taskId;
    private String userId;
    private String title;
    private LocalDate dateAdded;
    private LocalDate dateCompleted;
    private boolean isCompleted;
    private Priority priority;

}
