package com.ssafy.todo.repository;

import com.ssafy.todo.vo.Todo;

import java.util.List;

public interface TodoRepository {

    public List<Todo> getTodos();
    void deleteTodo(int id);
    void insertTodo(String content);
}
