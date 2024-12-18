package todoapp.todoapp_develop.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TodoResponseDto {
    private Long id;
    private String username;
    private String title;
    private String todo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
