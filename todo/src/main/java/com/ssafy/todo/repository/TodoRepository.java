package com.ssafy.todo.repository;

import com.ssafy.todo.dto.TodoGetDto;
import com.ssafy.todo.vo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TodoRepository {

    List<Todo> getTodos();
    Todo findOne(int id);
    void updateTodo(int id, Todo updatedTodo);
}
