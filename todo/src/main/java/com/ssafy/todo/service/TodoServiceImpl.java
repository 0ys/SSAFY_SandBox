package com.ssafy.todo.service;

import com.ssafy.todo.dto.TodoGetDto;
import com.ssafy.todo.repository.TodoQuerydslRepository;
import com.ssafy.todo.repository.TodoRepository;
import com.ssafy.todo.repository.TodoRepositoryImpl;
import com.ssafy.todo.vo.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService{

    @Autowired
    TodoRepository repository;

    @Autowired
    TodoQuerydslRepository querydslRepository;

    @Override
    public List<TodoGetDto> getTodos() {
        List<Todo> todos = repository.getTodos();
        //log.info("log");
        return todos.stream()
                .map(TodoGetDto::of)
                .collect(Collectors.toList());
        // List<Todo>를 List<TodoGetDto>로 변환
    }

    @Override
    public List<TodoGetDto> getTodosWithQuerydsl() {
        List<Todo> todos = querydslRepository.getTodosWithQuerydsl();
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
