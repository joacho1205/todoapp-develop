package todoapp.todoapp_develop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todoapp.todoapp_develop.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
