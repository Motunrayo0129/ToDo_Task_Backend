package com.todoList.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoList.data.models.Priority;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        response.setDateAdded(LocalDateTime.now());
        response.setCompleted(false);
        response.setDateCompleted(null);
        response.setPriority(Priority.HIGH);
    }

    @Test
    void addTask() throws Exception {
        when(taskServices.addTask(any(TaskRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/tasks/addTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));


    }

    @Test
    void editTask() {
        when(taskServices.editTask(any(TaskRequest.class), any(String.class)))
                .thenReturn(response);

        mockMvc.perform()
    }

    @Test
    void markAsCompleted() {
    }

    @Test
    void deleteTask() {
    }
}