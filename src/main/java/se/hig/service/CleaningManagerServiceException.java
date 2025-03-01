package se.hig.service;

/**
 * Simple, custom exception, created at the Service layer.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class CleaningManagerServiceException extends Exception {
    public CleaningManagerServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
