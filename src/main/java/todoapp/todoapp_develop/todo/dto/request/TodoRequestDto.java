package todoapp.todoapp_develop.todo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import todoapp.todoapp_develop.todo.domain.Todo;
import todoapp.todoapp_develop.user.domain.User;

@Getter
@NoArgsConstructor
public class TodoRequestDto {
    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 10, message = "제목은 10글자 이내여야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 500, message = "내용은 500자 이내여야 합니다.")
    private String content;

    public Todo toEntity(User user) {
        return Todo.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }

}
