package com.todoList.utils;

import com.todoList.data.models.ToDo;
import com.todoList.dtos.responses.TaskResponse;

import java.time.LocalDateTime;

public class Mapper {

    public static TaskResponse mapUserToUserResponse(ToDo updatedTask){
        TaskResponse response = new TaskResponse();
        response.setTaskId(updatedTask.getId());
        response.setCompleted(updatedTask.isCompleted());
        response.setTitle(updatedTask.getTitle());
        response.setDateCompleted(updatedTask.getDateCompleted());
        response.setPriority(updatedTask.getPriority());
        response.setDateAdded(LocalDateTime.now());
        response.setCompleted(updatedTask.isCompleted());

        return response;

    }
}
