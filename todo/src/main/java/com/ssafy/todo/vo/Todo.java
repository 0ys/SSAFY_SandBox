package com.ssafy.todo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Todo {

    @Id
    int id;

    String content;
    Boolean completed;
}
