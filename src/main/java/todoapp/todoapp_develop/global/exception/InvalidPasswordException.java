package todoapp.todoapp_develop.global.exception;

public class InvalidPasswordException extends CustomRuntimeException {
    public InvalidPasswordException(ErrorCode errorCode) {
        super(errorCode);
    }
}
