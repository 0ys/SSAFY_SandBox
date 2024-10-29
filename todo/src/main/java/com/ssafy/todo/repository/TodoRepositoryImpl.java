package com.ssafy.todo.repository;

import com.ssafy.todo.vo.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Todo> getTodos() {
        String query = "SELECT t FROM Todo t";
        return em.createQuery(query, Todo.class).getResultList();
    }

    @Override
    public void deleteTodo(int id) {
        String query = "DELETE FROM Todo t WHERE t.id = :id";
        em.createQuery(query).setParameter("id", id).executeUpdate();
    }

    @Override
    public void insertTodo(String content) {
        String query = "INSERT INTO Todo (content) VALUES (:content)";
        em.createQuery(query).setParameter("content", content).executeUpdate();
    }
}
