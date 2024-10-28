package com.ssafy.todo.controller;

import com.ssafy.todo.dto.TodoGetDto;
import com.ssafy.todo.service.TodoService;
import com.ssafy.todo.service.TodoServiceImpl;
import com.ssafy.todo.vo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping
    public Map<String, Object> getTodos() {
        List<TodoGetDto> todos = service.getTodos();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "정상적으로 요청되었습니다.");
        response.put("todos", todos);
        return response; //map -> json
    }
}
