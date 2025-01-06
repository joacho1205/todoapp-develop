package todoapp.todoapp_develop.global.exception;

public class UserNotExistingException extends CustomRuntimeException {
    public UserNotExistingException(ErrorCode errorCode) {
        super(errorCode);
    }
}

