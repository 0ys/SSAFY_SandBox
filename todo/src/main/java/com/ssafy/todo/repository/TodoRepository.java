package com.ssafy.todo.repository;

import com.ssafy.todo.vo.Todo;

import java.util.List;

public interface TodoRepository {

    void deleteTodo(int id);
    void insertTodo(String content);
    List<Todo> getTodos();
    Todo findOne(int id);
    void updateTodo(int id, Todo updatedTodo);
}
