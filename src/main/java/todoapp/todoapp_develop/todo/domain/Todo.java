package todoapp.todoapp_develop.todo.domain;

import lombok.*;
import jakarta.persistence.*;
import todoapp.todoapp_develop.user.domain.User;
import todoapp.todoapp_develop.global.domain.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "todos")
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Todo(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public boolean isWriter(User user) {
        return this.user.getId().equals(user.getId());
    }
}
