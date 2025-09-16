package com.todoList.controllers;

import com.todoList.dtos.requests.TaskRequest;
import com.todoList.services.TaskServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskServices taskServices;
    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @PostMapping("/addTask")
    public ResponseEntity<?> addTask(@RequestBody TaskRequest request){
        return ResponseEntity.ok(taskServices.addTask(request, request.getUserId()));
    }

    @PutMapping("/editTask/{taskId}")
    public ResponseEntity<?> editTask(@RequestBody TaskRequest request, @PathVariable String taskId){
        return ResponseEntity.ok(taskServices.editTask(request, taskId));
    }

    @PatchMapping("/markAsCompleted/{taskId}")
    public ResponseEntity<?> markAsCompleted(@PathVariable String taskId){
        return ResponseEntity.ok(taskServices.markAsCompleted(taskId));
    }

    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String taskId){
        taskServices.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }


}
