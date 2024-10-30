package com.ssafy.todo.repository;

import com.ssafy.todo.vo.Todo;

import java.util.List;

public interface TodoQuerydslRepository {
    List<Todo> getTodosWithQuerydsl();

}
