package ie.dempsey.kitchenstore.application.exceptions;

public class NoSuchProductException extends Exception {
    public static final String DEFAULT_MSG = "The required product does not exist.";

    public NoSuchProductException() {
        this(DEFAULT_MSG);
    }

    public NoSuchProductException(String message) {
        super(message);
    }

    public NoSuchProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
