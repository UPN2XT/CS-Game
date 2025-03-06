package exception;

/**
 * The InvalidMarbleException class is thrown when an invalid marble action is attempted in the game.
 * It extends the InvalidSelectionException class and provides constructors for creating exceptions with or without a message.
 */
public class InvalidMarbleException extends InvalidSelectionException {

    /**
     * Constructs a new InvalidMarbleException with no detail message.
     */
    public InvalidMarbleException() {
        super();
    }

    /**
     * Constructs a new InvalidMarbleException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidMarbleException(String message) {
        super(message);
    }
}