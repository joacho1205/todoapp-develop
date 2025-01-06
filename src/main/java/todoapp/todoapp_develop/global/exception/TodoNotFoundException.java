package todoapp.todoapp_develop.global.exception;

public class TodoNotFoundException extends CustomRuntimeException {
    public TodoNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}