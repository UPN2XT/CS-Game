package exception;

/**
 * The SplitOutOfRangeException class is thrown when a split action is attempted that is out of the valid range in the game.
 * It extends the InvalidSelectionException class and provides constructors for creating exceptions with or without a message.
 */
public class SplitOutOfRangeException extends InvalidSelectionException {

    /**
     * Constructs a new SplitOutOfRangeException with no detail message.
     */
    public SplitOutOfRangeException() {
        super();
    }

    /**
     * Constructs a new SplitOutOfRangeException with the specified detail message.
     *
     * @param message the detail message
     */
    public SplitOutOfRangeException(String message) {
        super(message);
    }
}