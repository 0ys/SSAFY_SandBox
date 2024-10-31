//package com.ssafy.todo.repository;
//
//import com.ssafy.todo.vo.Todo;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class TodoRepositoryImpl implements TodoRepository{
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public List<Todo> getTodos() {
//        String query = "SELECT t FROM Todo t";
//        return em.createQuery(query, Todo.class).getResultList();
//    }
//
//    @Override
//    public boolean deleteTodo(int id) {
//        String query = "DELETE FROM Todo t WHERE t.id = :id";
//        int cnt = em.createQuery(query).setParameter("id", id).executeUpdate();
//        return cnt >= 1;
//    }
//
//    @Override
//    public long insertTodo(String content) {
//        String query = "INSERT INTO Todo (content) VALUES (:content)";
//        em.createQuery(query).setParameter("content", content).executeUpdate();
//
//        return (long) em.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();
//    }
//
//    @Override
//    public boolean updateTodo(int id, Todo updatedTodo) {
//        String query = "UPDATE Todo t SET t.completed =:completed WHERE t.id =:id";
//        int cnt = em.createQuery(query)
//                .setParameter("completed", !updatedTodo.getCompleted())
//                .setParameter("id", id)
//                .executeUpdate();
//        return cnt >= 1;
//    }
//
//    @Override
//    public Todo findOne(int id) {
//        /* 이전 코드 */
//        /*
//        String query = "SELECT t FROM Todo t WHERE t.id =:id";
//        return em.createQuery(query, Todo.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        */
//
//        String query = "SELECT t FROM Todo t WHERE t.id = :id";
//        List<Todo> results = em.createQuery(query, Todo.class)
//                .setParameter("id", id)
//                .getResultList();
//
//        if (results.isEmpty()) {
//            throw new EntityNotFoundException("Todo not found with id: " + id);
//        }
//
//        return results.get(0);
//    }
//}
