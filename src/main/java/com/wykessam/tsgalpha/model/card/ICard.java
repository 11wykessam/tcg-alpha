package com.wykessam.tsgalpha.model.card;

import java.util.Set;

/**
 * @author Samuel Wykes.
 * Represents a card in the TCG.
 */
public interface ICard {

    /**
     * Get the original name of the card.
     * @return {@link String}.
     */
    String getOriginalName();

    /**
     * Get names that could apply to the card.
     * @return {@link String}.
     */
    Set<String> getCardNames();

    /**
     * Get the number of charges needed to use the card.
     * @return {@link Integer}.
     */
    Integer getRequirement();

    /**
     * Get the allegiance of the card.
     * @return {@link Allegiance} object.
     */
    Allegiance getAllegiance();

}
