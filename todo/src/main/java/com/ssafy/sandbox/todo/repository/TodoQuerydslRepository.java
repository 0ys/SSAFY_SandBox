package com.ssafy.sandbox.todo.repository;

import com.ssafy.sandbox.todo.vo.Todo;

import java.util.List;

public interface TodoQuerydslRepository {
    List<Todo> getTodosWithQuerydsl();

}
