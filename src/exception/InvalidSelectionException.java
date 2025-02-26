package exception;

public abstract class InvalidSelectionException extends GameException {
    public InvalidSelectionException(String message) {
        super(message);
    }
    public InvalidSelectionException() {
        super();
    }
}
