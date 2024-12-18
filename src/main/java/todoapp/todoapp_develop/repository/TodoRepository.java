package todoapp.todoapp_develop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todoapp.todoapp_develop.domain.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
