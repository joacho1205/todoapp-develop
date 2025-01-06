package todoapp.todoapp_develop.global.exception;

public class EmailNotFoundException extends CustomRuntimeException {
  public EmailNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
