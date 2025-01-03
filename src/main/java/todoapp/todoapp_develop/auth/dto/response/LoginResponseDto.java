package todoapp.todoapp_develop.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto {
    private String message; // 로그인 성공여부 메시지 출력
    private String username; // 비밀번호는 당연히 출력되지 말아야 한다
}
