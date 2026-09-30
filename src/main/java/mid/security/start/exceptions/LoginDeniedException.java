package mid.security.start.exceptions;

public class LoginDeniedException extends RuntimeException {
    public LoginDeniedException(String message) {
        super(message);
    }
}
