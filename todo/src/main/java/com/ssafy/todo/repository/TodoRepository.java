package com.ssafy.todo.repository;

import com.ssafy.todo.dto.TodoGetDto;
import com.ssafy.todo.vo.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TodoRepository {

    public List<Todo> getTodos();
}
