package exception;

/**
 * The IllegalDestroyException class is thrown when an illegal destroy action is attempted in the game.
 * It extends the ActionException class and provides constructors for creating exceptions with or without a message.
 */
public class IllegalDestroyException extends ActionException {

    /**
     * Constructs a new IllegalDestroyException with no detail message.
     */
    public IllegalDestroyException() {
        super();
    }

    /**
     * Constructs a new IllegalDestroyException with the specified detail message.
     *
     * @param message the detail message
     */
    public IllegalDestroyException(String message) {
        super(message);
    }
}