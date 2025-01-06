package todoapp.todoapp_develop.global.exception;

public class ExistingUserNameException extends CustomRuntimeException {
    public ExistingUserNameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
