package ie.dempsey.kitchenstore.application.exceptions;

public class NoSuchUserException extends Exception {
    public static final String DEFAULT_MSG = "The required user does not exist";

    public NoSuchUserException() {
        super(DEFAULT_MSG);
    }

    public NoSuchUserException(String msg) {
        super(msg);
    }

    public NoSuchUserException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
