package com.wykessam.tsgalpha.model.card;

/**
 * @author Samuel Wykes.
 * Represents a creature card in the TCG.
 */
public interface ICreatureCard extends ICard {

    /**
     * Get the type of the card.
     * @return {@link CreatureType}.
     */
    CreatureType getCardType();

    /**
     * Get the number of charges needed to use the card.
     * @return {@link Integer}.
     */
    Integer getRequirement();

    /**
     * Get the size of the card.
     * @return {@link Integer}.
     */
    Integer getSize();

    /**
     * Get the power value of the card.
     * @return {@link Integer}.
     */
    Integer getPower();

    /**
     * Get the critical value of the card.
     * @return {@link Integer}.
     */
    Integer getCritical();

}
