package com.ssafy.sandbox.todo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoGetDto {
    private int id;
    private String content;
    private boolean completed;

    /*
    - of 메소드
    : of 메소드는 보통 정적(static) 메소드로 구현되며, 여러 개의 인자를 받아 DTO 객체를 생성하는 역할.
    주로 생성자에 필요한 인자들을 파라미터로 받아서 DTO 객체를 생성하는 용도로 사용.

    - from 메소드
    : from 메소드는 보통 정적 메소드로 구현되며, 다른 객체를 기반으로 DTO를 생성하는 역할.
    주로 엔티티나 다른 DTO를 변환하여 현재 DTO로 매핑하는 용도로 사용.
     */
//    public static TodoGetDto from(Todo todo) {
//        TodoGetDto dto = new TodoGetDto();
//        dto.setId(todo.getId());
//        dto.setContent(todo.getContent());
//        dto.setCompleted(todo.getCompleted());
//        return dto;
//    }

//    // builder() 사용
//    public static TodoGetDto from(Todo todo){
//        return TodoGetDto.builder()
//                .id(todo.getId())
//                .content(todo.getContent())
//                .completed(todo.getCompleted())
//                .build();
//    }
}
