package exception;

/**
 * The CannotDiscardException class represents an exception that is thrown when a discard action cannot be performed.
 */
public class CannotDiscardException extends ActionException {
    /**
     * Constructs a new CannotDiscardException with no detail message.
     */
    public CannotDiscardException() {
        super();
    }

    /**
     * Constructs a new CannotDiscardException with the specified detail message.
     *
     * @param message the detail message
     */
    public CannotDiscardException(String message) {
        super(message);
    }
}