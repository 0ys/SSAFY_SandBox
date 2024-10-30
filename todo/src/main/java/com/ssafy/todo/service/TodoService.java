package com.ssafy.todo.service;

import com.ssafy.todo.dto.TodoGetDto;

import java.util.List;

public interface TodoService {
    List<TodoGetDto> getTodos();
//    List<TodoGetDto> getTodosWithQuerydsl();
    boolean deleteTodo(int id);
    boolean updateTodo(int id);
    long insertTodo(String content);
}
