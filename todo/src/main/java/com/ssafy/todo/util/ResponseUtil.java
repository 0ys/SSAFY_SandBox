package com.ssafy.todo.util;

import com.ssafy.todo.dto.TodoGetDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResponseUtil {

    public static Map<String, Object> createTodoListResponse(String message, List<TodoGetDto> todos) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("message", message);
        if (todos != null) {
            response.put("todos", todos);
        }
        return response;
    }

    public static ResponseEntity<String> createBooleanResponse(boolean isSuccess, String successMessage) {
        return isSuccess
                ? ResponseEntity.ok(successMessage)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("정상적이지 않은 요청입니다.");
    }
}