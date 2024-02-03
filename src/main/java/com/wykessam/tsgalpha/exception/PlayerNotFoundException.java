package com.wykessam.tsgalpha.exception;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Exception thrown when attempt is made to retrieve a player that does not exist.
 */
public class PlayerNotFoundException extends RuntimeException {

    /**
     * @param id {@link UUID}.
     */
    public PlayerNotFoundException(final UUID id) {
        super(String.format("Player with id '%s' not found.", id.toString()));
    }

}
