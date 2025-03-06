package exception;

/**
 * The IllegalMovementException class is thrown when an illegal movement action is attempted in the game.
 * It extends the ActionException class and provides constructors for creating exceptions with or without a message.
 */
public class IllegalMovementException extends ActionException {

    /**
     * Constructs a new IllegalMovementException with no detail message.
     */
    public IllegalMovementException() {
        super();
    }

    /**
     * Constructs a new IllegalMovementException with the specified detail message.
     *
     * @param message the detail message
     */
    public IllegalMovementException(String message) {
        super(message);
    }
}