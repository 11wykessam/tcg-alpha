package com.wykessam.tsgalpha.model.card;

/**
 * @author Samuel Wykes.
 */
public enum CreatureAllegiance {

    EMPIRE_OF_FIRE("Empire of Fire");

    private final String name;

    CreatureAllegiance(final String name) {
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
