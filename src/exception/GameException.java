package exception;

/**
 * The GameException class is an abstract base class for all game-related exceptions.
 * It extends the Exception class and provides constructors for creating exceptions with or without a message.
 */
public abstract class GameException extends Exception {

    /**
     * Constructs a new GameException with no detail message.
     */
    public GameException() {
        super();
    }

    /**
     * Constructs a new GameException with the specified detail message.
     *
     * @param message the detail message
     */
    public GameException(String message) {
        super(message);
    }
}