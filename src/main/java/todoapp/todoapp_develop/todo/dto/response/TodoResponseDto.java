package todoapp.todoapp_develop.todo.dto.response;

import lombok.Getter;
import todoapp.todoapp_develop.todo.domain.Todo;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.username = todo.getUser().getUsername();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
    }
}
