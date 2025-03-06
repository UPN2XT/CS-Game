package exception;

/**
 * The InvalidCardException class is thrown when an invalid card action is attempted in the game.
 * It extends the InvalidSelectionException class and provides constructors for creating exceptions with or without a message.
 */
public class InvalidCardException extends InvalidSelectionException {

    /**
     * Constructs a new InvalidCardException with no detail message.
     */
    public InvalidCardException() {
        super();
    }

    /**
     * Constructs a new InvalidCardException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidCardException(String message) {
        super(message);
    }
}