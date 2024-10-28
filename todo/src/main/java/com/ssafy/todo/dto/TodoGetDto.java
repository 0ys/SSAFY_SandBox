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
    private String resName;

    public static TodoGetDto of(Todo todo) {
        TodoGetDto dto = new TodoGetDto();
        dto.setId(todo.getId());
        dto.setContent(todo.getContent());
        dto.setCompleted(todo.getCompleted());
        dto.setResName("자원이름"); //vo와는 다른 속성 추가 가능
        return dto;
    }
}
