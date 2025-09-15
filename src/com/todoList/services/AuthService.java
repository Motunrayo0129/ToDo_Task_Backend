package com.todoList.services;

import com.todoList.data.repositories.RegistrationRepository;
import com.todoList.dtos.requests.LoginRequest;
import com.todoList.dtos.responses.LoginResponse;
import com.todoList.data.models.Registration;

public class LoginService {

    private RegistrationRepository registrationRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        Registration register = new Registration();

        register


        return null;

    }


}



//public AddDrugResponse addDrugResponse(AddDrugRequestDto requestDto) {
//    Drug drug = new Drug();
//    drug.setCategory(requestDto.getDrugCategory());
//    drug.setTypes(requestDto.getDrugTypes());
//    drug.setDrugName(requestDto.getDrugName());
//    drug.setQuantity(requestDto.getQuantity());
//    drug.setManufactureDate(requestDto.getManufactureDate());
//
//    LocalDate expiryDate = requestDto.getManufactureDate().plusMonths(6);
//    drug.setExpiryDate(expiryDate);
//
//    Drug saved = drugRepository.save(drug);