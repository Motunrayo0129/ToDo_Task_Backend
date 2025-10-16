package com.todoList.services;

import com.todoList.data.models.User;
import com.todoList.data.repositories.UserRepository;
import com.todoList.dtos.requests.LoginRequest;
import com.todoList.dtos.requests.RegistrationRequest;
import com.todoList.dtos.responses.LoginResponse;
import com.todoList.dtos.responses.RegistrationResponse;
import com.todoList.exceptions.InvalidLoginResponse;
import com.todoList.exceptions.InvalidRegistrationException;
import com.todoList.utils.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public RegistrationResponse register(RegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new InvalidRegistrationException("Email already exists");
        }

        String hashedPassword = HashPassword.hashPassword(request.getPassword());

        User user = new User();
        user.setPassword(hashedPassword);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        userRepository.save(user);

        RegistrationResponse response =new RegistrationResponse();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setMessage("Registration Successful");
        return response;
    }


    public LoginResponse login(LoginRequest request) {
        System.out.println(request.toString());
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!HashPassword.verifyPassword(request.getPassword(), user.getPassword())) {
            throw new InvalidLoginResponse("Invalid credentials");
        }


       LoginResponse response = new LoginResponse();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setMessage("Login Successful");

        return response;
    }


}
