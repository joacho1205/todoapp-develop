package todoapp.todoapp_develop.auth.dto.response;

import todoapp.todoapp_develop.user.domain.User;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final Long userId;
    private final String username;

    public LoginResponseDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
    }
}
