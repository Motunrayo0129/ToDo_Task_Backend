package com.todoList.data.repositories;

import com.todoList.data.models.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, String> {



}
