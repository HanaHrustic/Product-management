package hr.java.production.exception;

/**
 * exception for Item duplication
 */
public class DuplicateItemException extends Exception{
    /**
     * contractor which defines exception message
     * @param message explanation message for exception
     */
    public DuplicateItemException(String message) {
        super(message);
    }

    /**
     * contractor which defines exception message and cause
     * @param message explanation message for exception
     * @param cause item that causes the exception
     */
    public DuplicateItemException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * contractor which defines exception cause
     * @param cause item that causes the exception
     */
    public DuplicateItemException(Throwable cause) {
        super(cause);
    }
}
