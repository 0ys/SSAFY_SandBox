package com.ssafy.todo.service;

import com.ssafy.todo.repository.TodoRepository;
import com.ssafy.todo.vo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    TodoRepository repository;

    @Override
    public List<Todo> getTodos() {
        return repository.getTodos();
    }

    @Override
    public void deleteTodo(int id) {

    }

    @Override
    public void updateTodo(int id) {

    }

    @Override
    public void insertTodo(String content) {

    }
}
