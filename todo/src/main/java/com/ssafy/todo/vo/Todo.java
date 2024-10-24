package com.ssafy.todo.vo;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Todo {

    @Id
    int id;

    String content;
    Boolean completed;
}
