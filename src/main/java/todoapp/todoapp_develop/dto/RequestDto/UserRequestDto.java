package todoapp.todoapp_develop.dto.RequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "유저명이 비어있습니다.")
    private String username;

    @NotBlank(message = "이메일이 비어있습니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String email;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;
}
