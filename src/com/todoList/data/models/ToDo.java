package com.todoList.data.models;

import java.time.LocalDateTime;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("GateTwo")
public class ToDo {
    @Id
    private String id;
    private String title;
    private LocalDateTime dateAdded;
    private LocalDateTime dateCompleted;
    private boolean isCompleted;
    private Priority priority;

    private String userId;

}
