package ie.dempsey.kitchenstore.application.exceptions;

public class NoSuchHouseException extends Exception {
    public NoSuchHouseException() {
        this("The required house was not found.");
    }

    public NoSuchHouseException(String message) {
        super(message);
    }

    public NoSuchHouseException(String message, Throwable cause) {
        super(message, cause);
    }
}
