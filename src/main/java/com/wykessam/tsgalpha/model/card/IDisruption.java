package com.wykessam.tsgalpha.model.card;

import com.wykessam.tsgalpha.model.card.effect.IEffect;

/**
 * @author Samuel Wykes.
 * Represents a disruption card in the TCG.
 */
public interface IDisruption extends ICard {

    /**
     * Get the number of charges needed to use the card.
     * @return {@link Integer}.
     */
    Integer getRequirement();

}
