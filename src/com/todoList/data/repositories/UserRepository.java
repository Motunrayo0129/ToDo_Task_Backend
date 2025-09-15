package com.todoList.data.repositories;

import com.todoList.data.models.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RegistrationRepository extends MongoRepository<Registration, String> {

    Optional<Registration> findByEmail(String email);
    void deleteByEmail(String email);
    Optional<Registration> findByUserName(String userName);

}
