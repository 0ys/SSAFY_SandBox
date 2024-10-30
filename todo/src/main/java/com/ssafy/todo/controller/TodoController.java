package com.ssafy.todo.controller;

import com.ssafy.todo.dto.TodoGetDto;
import com.ssafy.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

//    @GetMapping
//    public ResponseEntity<Map<String, Object>> getTodosWithQuerydsl() {
//        List<TodoGetDto> todos = service.getTodosWithQuerydsl();
//        Map<String, Object> response = new LinkedHashMap<>();
//        if(todos != null && !todos.isEmpty()){
//            response.put("message", "정상적으로 요청되었습니다.");
//            response.put("todos", todos);
//        } else {
//            response.put("message", "정상적이지 않은 요청입니다.");
//        }
//        return ResponseEntity.ok(response);
//    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getTodos() {
        List<TodoGetDto> todos = service.getTodos();
        return (todos != null && !todos.isEmpty())
                ? ResponseEntity.ok(createTodoListResponse("정상적으로 요청되었습니다.", todos))
                : ResponseEntity.ok(createTodoListResponse("정상적이지 않은 요청입니다.", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id) {
        return createBooleanResponse(service.deleteTodo(id), id + "의 todo가 삭제되었습니다.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable("id") int id) {
        return createBooleanResponse(service.updateTodo(id), id + "의 completed가 정상적으로 토글되었습니다.");
    }

    @PostMapping
    public ResponseEntity<String> insertTodo(@RequestBody Map<String, String> todo) {
        String content = todo.get("content");
        long id = service.insertTodo(content);
        return createBooleanResponse(id > 0, id + "의 todo가 생성되었습니다.");
    }

    private Map<String, Object> createTodoListResponse(String message, List<TodoGetDto> todos) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", message);
        if (todos != null) {
            response.put("todos", todos);
        }
        return response;
    }

    private ResponseEntity<String> createBooleanResponse(boolean isSuccess, String successMessage) {
        return isSuccess
                ? ResponseEntity.ok(successMessage)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("정상적이지 않은 요청입니다.");
    }
}
