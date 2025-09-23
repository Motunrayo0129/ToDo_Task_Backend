package com.todoList.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoList.dtos.requests.LoginRequest;
import com.todoList.dtos.requests.RegistrationRequest;
import com.todoList.dtos.responses.LoginResponse;
import com.todoList.dtos.responses.RegistrationResponse;
import com.todoList.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

@WebMvcTest(controllers = LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    private LoginRequest loginRequest;
    private RegistrationRequest registrationRequest;

    @BeforeEach
    void setUp() {
        registrationRequest = new RegistrationRequest();
        registrationRequest.setPassword("Motunrayo");
        registrationRequest.setEmail("motun@gmail.com");

        loginRequest = new LoginRequest();
        loginRequest.setPassword("Motunrayo");
        loginRequest.setEmail("motun@gmail.com");
    }

    @Test
    void registration() throws Exception {
        RegistrationResponse response = new RegistrationResponse();
        response.setMessage("Registration Successful");

        when(authService.register(any(RegistrationRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Registration Successful"));

               // .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    void login() throws Exception {
        LoginResponse response = new LoginResponse();
        response.setMessage("Login Successful");

        when(authService.login(any(LoginRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}
