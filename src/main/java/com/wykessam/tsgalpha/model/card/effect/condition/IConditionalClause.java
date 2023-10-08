package com.wykessam.tsgalpha.model.card.effect.condition;

import com.wykessam.tsgalpha.model.card.effect.IClause;
import com.wykessam.tsgalpha.model.game.IGame;

/**
 * @author Samuel Wykes.
 * Reprsents a condition in a card clause.
 */
public interface IConditionalClause {

    /**
     * Resolve the condition based on the current state of the game.
     * @param game {@link IGame}.
     * @return {@link Boolean}.
     */
    Boolean resolve(final IGame game);

}
