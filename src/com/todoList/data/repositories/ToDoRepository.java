package com.todoList.data.repositories;

import com.todoList.data.models.ToDo;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, String> {
    Optional<ToDo> findByTitle(String title);


}
