package com.wykessam.tsgalpha.exception;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Exception thrown when attempt is made to retrieve a board that does not exist.
 */
public class BoardNotFoundException extends RuntimeException {

    /**
     * @param id {@link UUID}.
     */
    public BoardNotFoundException(final UUID id) {
        super(String.format("Board with id '%s' not found.", id.toString()));
    }

}
