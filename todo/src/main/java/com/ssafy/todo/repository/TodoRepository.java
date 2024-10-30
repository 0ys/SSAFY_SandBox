package com.ssafy.todo.repository;

import com.ssafy.todo.vo.Todo;

import java.util.List;

public interface TodoRepository {

    boolean deleteTodo(int id);
    long insertTodo(String content);
    List<Todo> getTodos();
    Todo findOne(int id);
    boolean updateTodo(int id, Todo updatedTodo);
}
