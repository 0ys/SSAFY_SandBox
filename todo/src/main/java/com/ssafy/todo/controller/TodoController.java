package com.ssafy.todo.controller;

import com.ssafy.todo.dto.TodoGetDto;
import com.ssafy.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TodoController {

    @Autowired
    TodoService service;

//    @GetMapping
//    public Map<String, List<TodoGetDto>> getTodos() {
//        List<TodoGetDto> todos = service.getTodos();
//        Map<String, List<TodoGetDto>> response = new HashMap<>();
//        response.put("todos", todos);
//        return response;
//    }

    @GetMapping
    public Map<String, List<TodoGetDto>> getTodosWithQuerydsl() {
        List<TodoGetDto> todos = service.getTodosWithQuerydsl();
        Map<String, List<TodoGetDto>> response = new HashMap<>();
        response.put("todos", todos);
        return response;
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable int id) {
        service.deleteTodo(id);
    }

    @PostMapping
    public void insertTodo(@RequestBody Map<String, String> todo) {
        String content = todo.get("content");
        service.insertTodo(content);
    }
}
