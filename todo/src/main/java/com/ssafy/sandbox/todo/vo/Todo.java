package com.ssafy.sandbox.todo.vo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter @Setter
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String content;

    @ColumnDefault("0")
    Boolean completed;
}
