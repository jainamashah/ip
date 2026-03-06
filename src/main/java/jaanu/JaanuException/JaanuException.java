package jaanu.JaanuException;

/**
 * Custom exception type used throughout the Jaanu application.
 * Allows user-friendly and personality-filled error messages.
 */
public class JaanuException extends Exception {
    /**
     * Creates a new {@code JaanuException} with the given message.
     *
     * @param message detail message describing the error
     */
    public JaanuException(String message) {
        super(message);
    }
}
