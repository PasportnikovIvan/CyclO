package dev.rentalService.exception;

/**
 * Exception thrown when a requested resource is not found.
 * This is a custom exception used to indicate that a resource could not be located.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
