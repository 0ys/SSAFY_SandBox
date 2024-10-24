package com.ssafy.todo.controller;

import com.ssafy.todo.service.TodoService;
import com.ssafy.todo.service.TodoServiceImpl;
import com.ssafy.todo.vo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class TodoController {

    @Autowired
    TodoService service;

    @GetMapping("/todos")
    public Model getTodos(Model model){
        List<Todo> todos = service.getTodos();
        model.addAttribute("todos", todos);
        return model;
    }
}
