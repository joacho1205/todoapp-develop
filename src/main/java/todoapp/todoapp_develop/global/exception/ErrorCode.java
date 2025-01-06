package todoapp.todoapp_develop.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다"), // UserNotExistingException
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "이메일이 존재하지 않습니다."), // EmailNotFoundException
    EXISTING_EMAIL(HttpStatus.BAD_REQUEST, "이미 사용중인 이메일입니다."), // ExistingEmailException
    EXISTING_USERNAME(HttpStatus.BAD_REQUEST, "이미 사용중인 사용자명입니다."), // ExistingUserNameException
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."), // InvalidPasswordException
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 할일입니다."), // TodoNotFoundException
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다."); // UnauthorizedException

    private final HttpStatus status;
    private final String message;
}
