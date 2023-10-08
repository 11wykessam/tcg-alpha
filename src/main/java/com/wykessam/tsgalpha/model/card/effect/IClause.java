package com.wykessam.tsgalpha.model.card.effect;

import com.wykessam.tsgalpha.model.card.effect.action.result.ResolutionResult;
import com.wykessam.tsgalpha.model.game.IGame;

/**
 * @author Samuel Wykes.
 * Represents a clause in a card effect.
 */
public interface IClause {

    /**
     * Resolve the effect of the clause.
     * @param game {@link IGame}.
     * @return {@link ResolutionResult}.
     */
    ResolutionResult resolve(final IGame game);

    /**
     * Get the current resolution state of the clause.
     * @return {@link ResolutionState}.
     */
    ResolutionState getResolutionState();

    /**
     * Reset the clause to the ready state.
     */
    void reset();

}
