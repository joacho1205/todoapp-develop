package todoapp.todoapp_develop.global.exception;

public class ExistingEmailException extends CustomRuntimeException {
    public ExistingEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}