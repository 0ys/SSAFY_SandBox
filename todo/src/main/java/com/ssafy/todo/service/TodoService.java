package com.ssafy.todo.service;

import com.ssafy.todo.vo.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodos();
    void deleteTodo(int id);
    void updateTodo(int id);
    void insertTodo(String content);
}
