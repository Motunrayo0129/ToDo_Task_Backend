package com.todoList.services;

import com.todoList.data.models.ToDo;
import com.todoList.data.models.User;
import com.todoList.data.repositories.ToDoRepository;
import com.todoList.data.repositories.UserRepository;
import com.todoList.dtos.requests.TaskRequest;
import com.todoList.dtos.responses.TaskResponse;
import com.todoList.exceptions.InvalidRegistrationException;
import com.todoList.exceptions.TaskNotFoundException;
import com.todoList.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;



@Service
public class TaskServices {
   private final ToDoRepository toDoRepository;
   private final UserRepository userRepository;

  @Autowired
  public TaskServices(ToDoRepository toDoRepository, UserRepository userRepository) {
      this.toDoRepository = toDoRepository;
      this.userRepository = userRepository;
  }

   public TaskResponse addTask(TaskRequest request, String userId) {
       if (request.getTitle().isEmpty()) {
           throw new IllegalArgumentException("Task title cannot be empty");
       }
       if (request.getPriority() == null) {
           throw new IllegalArgumentException("Task priority cannot be empty");
       }

       ToDo task = new ToDo();
       task.setUserId(userId);
       task.setTitle(request.getTitle());
       task.setPriority(request.getPriority());
       task.setDateCompleted(null);
       task.setCompleted(false);
       task.setUserId(request.getUserId());


       ToDo savedTask = toDoRepository.save(task);

       User user = userRepository.findById(request.getUserId())
               .orElseThrow(() -> new InvalidRegistrationException("User not found"));

       user.setTask(List.of(savedTask));

       userRepository.save(user);

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
       task.setDateCompleted(LocalDate.now());

       ToDo updatedTask = toDoRepository.save(task);

       return Mapper.mapUserToUserResponse(updatedTask);

   }

   public TaskResponse unmarkAsCompleted(String taskId){
      ToDo task = toDoRepository.findById(taskId)
              .orElseThrow(() -> new TaskNotFoundException("Task not found"));
      task.setCompleted(false);
      task.setDateCompleted(null);

      ToDo updatedTask = toDoRepository.save(task);

      return Mapper.mapUserToUserResponse(updatedTask);
   }

   public List<TaskResponse> getAllTasks(String userId) {
       List<ToDo> tasks = toDoRepository.findByUserId(userId);
       return tasks.stream().map(Mapper::mapUserToUserResponse).toList();

   }

   public void deleteTask(String taskId) {
       toDoRepository.deleteById(taskId);
   }

   public TaskResponse updateTask(String taskId, TaskRequest request)  {
       TaskResponse taskResponse = editTask(request, taskId);
       return taskResponse;
   }



}
