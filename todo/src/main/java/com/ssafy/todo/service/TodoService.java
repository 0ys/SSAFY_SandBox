package com.ssafy.todo.service;

import com.ssafy.todo.dto.TodoGetDto;
import com.ssafy.todo.vo.Todo;

import java.util.List;

public interface TodoService {
    List<TodoGetDto> getTodos();
    List<TodoGetDto> getTodosWithQuerydsl();
    void deleteTodo(int id);
    void updateTodo(int id);
    void insertTodo(String content);
}
