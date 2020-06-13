package ie.dempsey.kitchenstore.application.exceptions;

public class NoSuchProductException extends Exception {
    public NoSuchProductException() {
        this("The required product was not found.");
    }

    public NoSuchProductException(String message) {
        super(message);
    }

    public NoSuchProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
