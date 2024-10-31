package com.ssafy.sandbox.todo.repository;

import com.ssafy.sandbox.todo.vo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer>, TodoQuerydslRepository {

    // JpaRepository를 상속받기 때문에 더이상 사용하지 않음
//    boolean deleteTodo(int id);
//    long insertTodo(String content);
//    List<Todo> getTodos();
//    Todo findOne(int id);
//    boolean updateTodo(int id, Todo updatedTodo);
}
