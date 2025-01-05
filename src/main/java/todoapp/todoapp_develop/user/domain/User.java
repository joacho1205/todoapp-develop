package todoapp.todoapp_develop.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import todoapp.todoapp_develop.global.config.PasswordEncoder;
import todoapp.todoapp_develop.global.domain.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void validatePassword(String rawPassword, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(rawPassword, this.password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    public void validateUpdate(Long loginUserId) {
        if (!this.id.equals(loginUserId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
    }

    public void validateDelete(Long loginUserId) {
        if (!this.id.equals(loginUserId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
    }

    public void update(String username) {
        this.username = username;
    }
}
