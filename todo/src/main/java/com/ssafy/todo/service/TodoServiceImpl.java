package com.ssafy.todo.service;

import com.ssafy.todo.dto.TodoGetDto;
import com.ssafy.todo.repository.TodoQuerydslRepository;
import com.ssafy.todo.repository.TodoRepository;
import com.ssafy.todo.vo.Todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository repository;

    @Autowired
    private TodoQuerydslRepository querydslRepository;

    @Override
    public List<TodoGetDto> getTodos() {
        List<Todo> todos = repository.getTodos();
        //log.info("log");
        return todos.stream()
                .map(TodoGetDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoGetDto> getTodosWithQuerydsl() {
        List<Todo> todos = querydslRepository.getTodosWithQuerydsl();
        return todos.stream()
                .map(TodoGetDto::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteTodo(int id) {
        return repository.deleteTodo(id);
    }

    @Override
    @Transactional
    public boolean updateTodo(int id) {
        Todo updateTodo = repository.findOne(id);
        return repository.updateTodo(id, updateTodo);
    }

    @Override
    @Transactional
    public long insertTodo(String content) {
        return repository.insertTodo(content);
    }
}
