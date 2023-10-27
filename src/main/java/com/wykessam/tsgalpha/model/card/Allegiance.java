package com.wykessam.tsgalpha.model.card;

/**
 * @author Samuel Wykes.
 * Enum representing the allegiance of a given card.
 */
public enum Allegiance {

    EMPIRE_OF_FIRE("Empire of Fire"),
    SHADOW_LEGION("Shadow Legion");

    private final String name;

    Allegiance(final String name) {
        this.name = name;
    }

    /**
     * Returns the name of the allegiance.
     * @return {@link String}.
     */
    @Override
    public String toString() {
        return this.name;
    }

}
