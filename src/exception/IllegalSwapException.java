package exception;

/**
 * The IllegalSwapException class is thrown when an illegal swap action is attempted in the game.
 * It extends the ActionException class and provides constructors for creating exceptions with or without a message.
 */
public class IllegalSwapException extends ActionException {

    /**
     * Constructs a new IllegalSwapException with no detail message.
     */
    public IllegalSwapException() {
        super();
    }

    /**
     * Constructs a new IllegalSwapException with the specified detail message.
     *
     * @param message the detail message
     */
    public IllegalSwapException(String message) {
        super(message);
    }
}