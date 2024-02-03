package com.wykessam.tsgalpha.exception;

/**
 * @author Samuel Wykes.
 * Exception thrown when an attempt is made to create a user with a username that already exists.
 */
public class UserExistsException extends RuntimeException {

    /**
     * @param username {@link String}.
     */
    public UserExistsException(final String username) {
        super(String.format("User with username '%s' already exists", username));
    }

}
