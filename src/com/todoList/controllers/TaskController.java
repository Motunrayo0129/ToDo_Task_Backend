package com.todoList.controllers;

import com.todoList.dtos.requests.TaskRequest;
import com.todoList.services.TaskServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskServices taskServices;


    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addTask")
    public ResponseEntity<?> addTask(@RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskServices.addTask(request, request.getUserId()));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/editTask/{taskId}")
    public ResponseEntity<?> editTask(@RequestBody TaskRequest request, @PathVariable String taskId){
        return ResponseEntity.ok(taskServices.editTask(request, taskId));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/updateTask/{taskId}")
    public ResponseEntity<?> updateTask(@RequestBody TaskRequest request, @PathVariable String taskId){
        return ResponseEntity.ok(taskServices.updateTask(taskId, request));
    }

    @CrossOrigin(origins = "*")
    @PatchMapping("/markAsCompleted/{taskId}")
    public ResponseEntity<?> markAsCompleted(@PathVariable String taskId){
        return ResponseEntity.ok(taskServices.markAsCompleted(taskId));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping("/unmarkAsCompleted/{taskId}")
    public ResponseEntity<?> unmarkAsCompleted(@PathVariable String taskId){
        return ResponseEntity.ok(taskServices.unmarkAsCompleted(taskId));
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/allTask/{userId}")
    public ResponseEntity<?> getAllTasks(@PathVariable String userId){
        return ResponseEntity.ok(taskServices.getAllTasks(userId));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String taskId){
        taskServices.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }


}
