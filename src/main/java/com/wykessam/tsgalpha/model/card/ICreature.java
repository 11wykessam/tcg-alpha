package com.wykessam.tsgalpha.model.card;

import java.util.Set;

/**
 * @author Samuel Wykes.
 * Represents a creature card in the TCG.
 */
public interface ICreature extends ICard {

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
     * Get the summoning requirement for the card.
     * @return {@link Integer}.
     */
    Integer getSummoningRequirement();

    /**
     * Get the type of the card.
     * @return {@link CreatureType}.
     */
    CreatureType getCardType();

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

    /**
     * Get the effect of the card.
     * @return {@link ICreatureEffect} object.
     */
    ICreatureEffect getEffect();

}
