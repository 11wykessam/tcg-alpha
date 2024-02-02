package com.wykessam.tsgalpha.exception;

/**
 * @author Samuel Wykes.
 * Exception thrown when attempt is made to retrieve a user that does not exist.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * @param username {@link String}.
     */
    public UserNotFoundException(final String username) {
        super(String.format("Could not find user with username '%s'", username));
    }

}
