package com.ssafy.todo.controller;

import com.ssafy.todo.dto.TodoGetDto;
import com.ssafy.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
    public ResponseEntity<Map<String, Object>> getTodosWithQuerydsl() {
        List<TodoGetDto> todos = service.getTodosWithQuerydsl();
        Map<String, Object> response = new LinkedHashMap<>();
        if(todos != null && !todos.isEmpty()){
            response.put("message", "정상적으로 요청되었습니다.");
            response.put("todos", todos);
        } else {
            response.put("message", "정상적이지 않은 요청입니다.");
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable("id") int id){
        if(service.updateTodo(id)){
            return ResponseEntity.ok(id+"의 completed가 정상적으로 토글되었습니다.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("정상적이지 않은 요청입니다.");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id) {
        if(service.deleteTodo(id)){
            return ResponseEntity.ok(id+"의 todo가 삭제되었습니다.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("정상적이지 않은 요청입니다.");
        }
    }

    @PostMapping
    public ResponseEntity<String> insertTodo(@RequestBody Map<String, String> todo) {
        String content = todo.get("content");
        long id = service.insertTodo(content);
        if(id != -1){
            return ResponseEntity.ok(id+"의 todo가 생성되었습니다.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("정상적이지 않은 요청입니다.");
        }

    }
}
