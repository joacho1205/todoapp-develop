package todoapp.todoapp_develop.todo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import todoapp.todoapp_develop.todo.domain.Todo;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @EntityGraph(attributePaths = {"user"})
    List<Todo> findAllByOrderByUpdatedAtDesc();
}
