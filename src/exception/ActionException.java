package exception;

/**
 * The ActionException class is an abstract class that extends the GameException class.
 * It represents exceptions that occur during game actions.
 */
public abstract class ActionException extends GameException {
    /**
     * Constructs a new ActionException with no detail message.
     */
    public ActionException() {
        super();
    }

    /**
     * Constructs a new ActionException with the specified detail message.
     *
     * @param message the detail message
     */
    public ActionException(String message) {
        super(message);
    }
}