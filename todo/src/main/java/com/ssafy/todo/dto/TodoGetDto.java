package com.ssafy.todo.dto;

import com.ssafy.todo.vo.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoGetDto {
    private int id;
    private String content;
    private boolean completed;

    public static TodoGetDto of(Todo todo) {
        TodoGetDto dto = new TodoGetDto();
        dto.setId(todo.getId());
        dto.setContent(todo.getContent());
        dto.setCompleted(todo.getCompleted());
        return dto;
    }
}
