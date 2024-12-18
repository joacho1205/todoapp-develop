package todoapp.todoapp_develop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todoapp.todoapp_develop.domain.Todo;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findById(Long Id);
}
