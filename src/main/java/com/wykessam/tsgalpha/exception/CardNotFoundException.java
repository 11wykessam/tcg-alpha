package com.wykessam.tsgalpha.exception;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Exception thrown when attempt is made to retrieve a card that does not exist.
 */
public class CardNotFoundException extends RuntimeException {

    /**
     * @param id {@link UUID}.
     */
    public CardNotFoundException(final UUID id) {
        super(String.format("Card with id '%s' not found.", id.toString()));
    }

}
