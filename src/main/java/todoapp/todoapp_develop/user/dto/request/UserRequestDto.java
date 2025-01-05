package todoapp.todoapp_develop.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import todoapp.todoapp_develop.global.config.PasswordEncoder;
import todoapp.todoapp_develop.user.domain.User;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "유저명은 필수입니다.")
    @Size(max = 4, message = "유저명은 4글자 이내여야 합니다.")
    private String username;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,15}$",
            message = "비밀번호는 8-15자리이며, 알파벳, 숫자, 특수문자를 포함해야 합니다.")
    private String password;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))  // 비밀번호 암호화
                .build();
    }
}
