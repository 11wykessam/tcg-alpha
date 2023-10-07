package com.wykessam.tsgalpha.model.card;

import java.util.Set;

/**
 * @author Samuel Wykes.
 * Represents a disruption card in the TCG.
 */
public interface IDisruption extends ICard {

    /**
     * Get the effect of the card.
     * @return {@link IDisruptionEffect}.
     */
    IDisruptionEffect getEffect();

}
