package com.todoList.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoList.data.models.Priority;
import com.todoList.data.models.ToDo;
import com.todoList.dtos.requests.TaskRequest;
import com.todoList.dtos.responses.TaskResponse;
import com.todoList.services.TaskServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskServices taskServices;

    @Autowired
    private ObjectMapper objectMapper;

    private TaskRequest request;
    private TaskResponse response;

    @BeforeEach
    void setUp() {
        request = new TaskRequest();
        request.setPriority(Priority.HIGH);
        request.setTitle("Test Task");

        response = new TaskResponse();
        response.setTaskId("123456");
        response.setTitle("Test Task");
//        response.setDateAdded();
        response.setCompleted(false);
        response.setDateCompleted(null);
        response.setPriority(Priority.HIGH);
    }

    @Test
    void addTask() throws Exception {
        when(taskServices.addTask(any(TaskRequest.class), any(String.class)))
                .thenReturn(response);
        mockMvc.perform(post("/api/tasks/addTask/123456")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    void editTask() throws Exception {
        when(taskServices.editTask(any(TaskRequest.class), any(String.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/tasks/editTask/1234569")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
    @Test
    void markAsCompleted() throws Exception {
        String taskId = "123456";

        TaskResponse markAsComplete;

        markAsComplete = new TaskResponse();
        markAsComplete.setTaskId("123456");
        markAsComplete.setTitle("Test Task");
//        markAsComplete.setDateAdded();
        markAsComplete.setCompleted(true);
        markAsComplete.setDateCompleted(null);
        markAsComplete.setPriority(Priority.HIGH);

        when(taskServices.markAsCompleted(taskId))
        .thenReturn(markAsComplete);

        mockMvc.perform(patch("/api/tasks/markAsCompleted/"+taskId)
                .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.taskId").value(taskId))
                .andExpect(jsonPath("$.completed").value(true));

    }

    @Test
    void deleteTask() throws Exception {
        String taskId = "123456";
        mockMvc.perform(delete("/api/tasks/deleteTask/"+taskId))
                .andExpect(status().isOk());
        Mockito.verify(taskServices, Mockito.times                          (1)).deleteTask(taskId);
        Mockito.verifyNoMoreInteractions(taskServices);

    }

    @Test
    void testEditTask() {
        String taskId = "123456";

    }

    @Test
    void getAllTasks() {
    }
}