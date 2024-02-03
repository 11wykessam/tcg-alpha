package com.wykessam.tsgalpha.exception;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Exception thrown when an attempt is made to retrieve a game that does not exist.
 */
public class GameNotFoundException extends RuntimeException {

    /**
     * @param id {@link UUID}.
     */
    public GameNotFoundException(final UUID id) {
        super(String.format("Game with id '%s' not found.", id.toString()));
    }

}
