package com.ssafy.todo.service;

import com.ssafy.todo.dto.TodoGetDto;
import com.ssafy.todo.repository.TodoRepository;
import com.ssafy.todo.repository.TodoRepositoryImpl;
import com.ssafy.todo.vo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    TodoRepository repository;

    @Override
    public List<TodoGetDto> getTodos() {
        List<Todo> todos = repository.getTodos();
        return todos.stream()
                .map(TodoGetDto::of)
                .collect(Collectors.toList());
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
