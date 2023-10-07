package com.wykessam.tsgalpha.model.card;

/**
 * @author Samuel Wykes.
 * Represents the different types of creatures in the TCG.
 */
public enum CreatureType {

    DRAGON("Dragon");

    private final String name;

    CreatureType(final String name) {
        this.name = name;
    }

    /**
     * Returns the name of the type.
     * @return {@link String}.
     */
    @Override
    public String toString() {
        return this.name;
    }

}
