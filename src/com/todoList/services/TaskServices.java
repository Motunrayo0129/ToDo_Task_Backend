package com.todoList.services;

import com.todoList.data.models.ToDo;
import com.todoList.data.repositories.ToDoRepository;
import com.todoList.dtos.requests.TaskRequest;
import com.todoList.dtos.responses.TaskResponse;
import com.todoList.exceptions.TaskNotFoundException;
import com.todoList.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TaskServices {
   private final ToDoRepository toDoRepository;

   @Autowired
   public TaskServices(ToDoRepository toDoRepository) {
       this.toDoRepository = toDoRepository;
   }

   public TaskResponse addTask(TaskRequest request) {
       if (request.getTitle().isEmpty()) {
           throw new IllegalArgumentException("Task title cannot be empty");
       }
       if (request.getPriority() == null) {
           throw new IllegalArgumentException("Task priority cannot be empty");
       }

       ToDo task = new ToDo();
       task.setTitle(request.getTitle());
       task.setPriority(request.getPriority());
       task.setDateCompleted(null);
       task.setCompleted(false);


       ToDo savedTask = toDoRepository.save(task);

       return Mapper.mapUserToUserResponse(savedTask);

   }

   public TaskResponse editTask(TaskRequest request, String taskId) {
       ToDo task = toDoRepository.findById(taskId)
               .orElseThrow(() -> new TaskNotFoundException("Task not found"));

       task.setTitle(request.getTitle());
       task.setPriority(request.getPriority());


       ToDo updatedTask = toDoRepository.save(task);

       return Mapper.mapUserToUserResponse(updatedTask);

   }

   public TaskResponse markAsCompleted(String taskId) {
       ToDo task = toDoRepository.findById(taskId)
               .orElseThrow(() -> new TaskNotFoundException("Task not found"));
       task.setCompleted(true);
       task.setDateCompleted(LocalDateTime.now());

       ToDo updatedTask = toDoRepository.save(task);

       return Mapper.mapUserToUserResponse(updatedTask);

   }

   public void deleteTask(String taskId) {
       toDoRepository.deleteById(taskId);
   }



}
