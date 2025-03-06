package exception;

/**
 * The InvalidSelectionException class is an abstract class that is thrown when an invalid selection action is attempted in the game.
 * It extends the GameException class and provides constructors for creating exceptions with or without a message.
 */
public abstract class InvalidSelectionException extends GameException {

    /**
     * Constructs a new InvalidSelectionException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidSelectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidSelectionException with no detail message.
     */
    public InvalidSelectionException() {
        super();
    }
}