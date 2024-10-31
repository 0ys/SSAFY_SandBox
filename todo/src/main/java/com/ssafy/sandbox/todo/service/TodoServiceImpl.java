package com.ssafy.sandbox.todo.service;

import com.ssafy.sandbox.todo.dto.TodoGetDto;
import com.ssafy.sandbox.todo.repository.TodoRepository;
import com.ssafy.sandbox.todo.vo.Todo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public List<TodoGetDto> getTodos() {
        List<Todo> todos = repository.findAll();
        return todos.stream().map(todo -> TodoGetDto.builder()
                .id(todo.getId())
                .content(todo.getContent())
                .completed(todo.getCompleted())
                .build()).collect(Collectors.toList());
    }

//    @Override
//    public List<TodoGetDto> getTodosWithQuerydsl() {
//        List<Todo> todos = repository.getTodosWithQuerydsl();
//        return todos.stream()
//                .map(TodoGetDto::from)
//                .collect(Collectors.toList());
//    }

    @Override
    @Transactional
    public boolean deleteTodo(int id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean updateTodo(int id) {
        Todo todo = repository.findById(id).orElse(null);
        if (todo == null) {
            return false;
        }
        todo.setCompleted(!todo.getCompleted());
        repository.save(todo);
        return true;

    }

    @Override
    @Transactional
    public long insertTodo(String content) {
        Todo todo = Todo.builder()
                .content(content)
                .build();

        Todo savedTodo = repository.save(todo);
        return savedTodo.getId();

    }

}
