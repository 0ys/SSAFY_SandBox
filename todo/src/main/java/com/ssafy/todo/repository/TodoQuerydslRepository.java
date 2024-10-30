package com.ssafy.todo.repository;

import com.ssafy.todo.vo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoQuerydslRepository {
    List<Todo> getTodosWithQuerydsl();

}
