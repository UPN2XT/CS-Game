package exception;

/**
 * The CannotFieldException class represents an exception that is thrown when a field action cannot be performed.
 */
public class CannotFieldException extends ActionException {
    /**
     * Constructs a new CannotFieldException with no detail message.
     */
    public CannotFieldException() {
        super();
    }

    /**
     * Constructs a new CannotFieldException with the specified detail message.
     *
     * @param message the detail message
     */
    public CannotFieldException(String message) {
        super(message);
    }
}