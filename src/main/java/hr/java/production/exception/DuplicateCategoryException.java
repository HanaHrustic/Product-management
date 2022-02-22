package hr.java.production.exception;

/**
 * exception for Category duplication
 */
public class DuplicateCategoryException extends RuntimeException {
    /**
     * contractor which defines exception message
     * @param message explanation message for exception
     */
    public DuplicateCategoryException(String message) {
        super(message);
    }

    /**
     * contractor which defines exception message and cause
     * @param message explanation message for exception
     * @param cause item that causes the exception
     */
    public DuplicateCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * contractor which defines exception cause
     * @param cause item that causes the exception
     */
    public DuplicateCategoryException(Throwable cause) {
        super(cause);
    }
}
