package com.ssafy.todo.service;

import com.ssafy.todo.dto.TodoGetDto;
import com.ssafy.todo.repository.TodoQuerydslRepository;
import com.ssafy.todo.repository.TodoRepository;
import com.ssafy.todo.vo.Todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository repository;

//    @Autowired
//    private TodoQuerydslRepository querydslRepository;

//    @Override
//    public List<TodoGetDto> getTodos() {
//        List<Todo> todos = repository.getTodos();
//        //log.info("log");
//        return todos.stream()
//                .map(TodoGetDto::of)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<TodoGetDto> getTodosWithQuerydsl() {
        List<Todo> todos = repository.getTodosWithQuerydsl();
        return todos.stream()
                .map(TodoGetDto::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteTodo(int id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }

    }

    @Override
    @Transactional
    public boolean updateTodo(int id) {
        try {
            Todo todo = repository.findById(id).orElse(null);
            todo.setCompleted(!todo.getCompleted());
            repository.save(todo);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }

    }

    @Override
    @Transactional
    public long insertTodo(String content) {
        Todo todo = new Todo();
        todo.setContent(content);
        try {
            repository.save(todo);
            return todo.getId(); //wow
        } catch (Exception e) {
            log.error(e.getMessage());
            return -1;
        }
    }
}
