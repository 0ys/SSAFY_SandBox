package com.ssafy.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.todo.vo.QTodo;
import com.ssafy.todo.vo.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoQuerydslRepositoryImpl implements TodoQuerydslRepository {

    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory queryFactory;

    public TodoQuerydslRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Todo> getTodosWithQuerydsl() {
        QTodo todo = QTodo.todo;

        return queryFactory
                .selectFrom(todo)
                .fetch();
    }
}
